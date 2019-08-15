package com.wj.main;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class YouXiang {
    public static void main(String[] args) {
        //sendMail("wangyuyan@wj.com","12345");
        receiveMail();
    }
    /**
     * 发送邮件方法
     * @param toAddress 收件人
     * @param code 激活码：程序中产生一个随机的激活码
     */
    public static void sendMail(String toAddress,String code){
        //1,获取连接
        Properties props=new Properties();
        props.setProperty("mail.host", "localhost");//邮件服务器主机
        Session session= Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO
                return new PasswordAuthentication("zhaoliying@wj.com", "123");
            }
        });
        //2,创建邮件对象
        Message msg=new MimeMessage(session);
        try {
            //设置发件人
            msg.setFrom(new InternetAddress("service@xasxt.com"));
            //设置收件人
            msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAddress));
            //设置邮件标题
            msg.setSubject("来自西安尚学堂的激活邮件");
            //设置邮件正文（注意：实际使用中，这里的超链接最好写成ip地址，而不能写成localhost）
            msg.setContent("<h1>西安尚学堂友情提示您：</h1><h3>点击下面的链接激活邮件</h3><a href='http://localhost:8080/MailTest/jihuo.jsp?code="+code+"'>激活链接</a>", "text/html;charset=utf-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //3,发送邮件
        try {
            Transport.send(msg);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public  static void receiveMail(){
        Properties props=new Properties();
        props.setProperty("mail.host", "localhost");//邮件服务器主机
        Session session=Session.getInstance(props);
        Store store= null;
        try {
            store = session.getStore("pop3");
            store.connect("localhost","wangyuyan@wj.com","123");//只需要用户名，不能设成地址
            Folder f=store.getFolder("inbox");
            f.open(Folder.READ_WRITE);
            Message[]msgs=f.getMessages();
            for(Message m:msgs){
                System.out.println(m.getFileName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
