package com.wj.util;

import com.github.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class PayDemo {
    @Autowired
    private PayConfig payConfig;
    //付款生成url
    public  String testPay(String orderId){
        WXPay wxPay = new WXPay(payConfig);
        try {
            Map<String, String> data = new HashMap<String,String>();
            // 商品描述
            data.put("body", "西安粤嵌测试");
            // 订单号
            data.put("out_trade_no", orderId);
            //货币
            data.put("fee_type", "CNY");
            //金额，单位是分
            data.put("total_fee", "1");
            //调用微信支付的终端IP（estore商城的IP）
            data.put("spbill_create_ip", "127.0.0.1");
            //回调地址
            data.put("notify_url", "http://test.yq.com/wxpay/notify");
            // 交易类型为扫码支付
            data.put("trade_type", "NATIVE");
            //商品id,使用假数据
            data.put("product_id", "1234567");

            Map<String, String> result = wxPay.unifiedOrder(data);
            for (Map.Entry<String,String> entry :result.entrySet()){
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
            System.out.println("--------------------------------------------");
            if ("SUCCESS".equals(result.get("return_code"))) {
                String url = result.get("code_url");
                return url;
            } else {
                System.out.println("创建预交易订单失败，错误信息");
                return "创建预交易订单失败，错误信息";
            }
        } catch (Exception e) {
            System.out.println("创建预交易订单异常");
            e.printStackTrace();
            return "创建预交易订单异常";
        }
    }
    //查询是否付款成功
    public String queryPay(String orderId){
        WXPay wxPay = new WXPay(payConfig);
        Map<String, String> data = new HashMap<>();
        // 订单号
        data.put("out_trade_no", orderId);
        try {
            Map<String, String> result = wxPay.orderQuery(data);
            if (result == null) {
                System.out.println("未查询到结果，未付款！");
            }
            String state = result.get("trade_state");
            System.out.println(state);
            if ("SUCCESS".equals(state)) {
                // success，则认为付款成功
                System.out.println("付款成功！");
                return "付款成功！";
                // 修改订单状态为已付款
            }else {
                //USERPAYING,NOTPAY
                System.out.println("未付款或正在付款，都认为是未付款");
                return "未付款或正在付款，都认为是未付款";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "未付款或正在付款，都认为是未付款";
        }
    }
}
