package com.wj.contorller;

import com.github.pagehelper.PageInfo;
import com.wj.pojo.Spu;
import com.wj.service.ISkuService;
import com.wj.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private ISpuService spuService;
    @Autowired
    private ISkuService skuService;
    @RequestMapping("/getById")
    public PageInfo getById(Long cid1, Long cid2, Long cid3,Integer page,Integer size){
        List<Spu> list=spuService.getById(cid1, cid2, cid3,page,size);
        for (Spu s:list) {
            s.setSkus(skuService.getById(s.getId()));
        }
        PageInfo pageInfo=new PageInfo(list);
        return  pageInfo;
    }
}
