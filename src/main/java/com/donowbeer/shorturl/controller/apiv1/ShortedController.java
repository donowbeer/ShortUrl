package com.donowbeer.shorturl.controller.apiv1;

import com.donowbeer.shorturl.bean.ResponseData;
import com.donowbeer.shorturl.bean.Shorted;
import com.donowbeer.shorturl.bean.Viewed;
import com.donowbeer.shorturl.logic.MyStringUtil;
import com.donowbeer.shorturl.service.ShortedService;
import com.donowbeer.shorturl.service.ViewedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("")
public class ShortedController {

    @Autowired
    ShortedService shortedService;

    @Autowired
    ViewedService viewedService;

    private String host = "http://119.23.226.102/ShortUrl/";



    @RequestMapping(value = "/v1/shorted", method = RequestMethod.GET)
    @ResponseBody
    public List<Shorted> getAllShorted() {
        return shortedService.selectAll();
    }

    @RequestMapping(value = "/v1/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData createShortUrl(@RequestBody Shorted inputData) {
        //1输入数据校验
        //2url是否合法
        //3判断数据库是否已存在，若存在返回存在的，不存在创建后保存
        //4返回结果



        //1
        if (inputData.getLongUrl() == null ||
                inputData.getLongUrl() == "") {
            return ResponseData.badLongUrl().putDataValue("ErrMsg", "获取请求体失败");
        }

        //2 longUrl是否是合法的网址
        String longUrl = inputData.getLongUrl();
        if (!longUrl.matches(MyStringUtil.URL_REG)) {
            return ResponseData.badLongUrl().putDataValue("ErrMsg", "不是合法的url,注意以http或https开头");
        }

        //3 数据库是否存在
        String keyword = shortedService.selectOneSpecifiedByLongUrl(longUrl);
        if (keyword == null ||
                keyword == "") {
            //数据库不存在  查看用户是否自定义了短网址关键字
            if (inputData.getKeyword()==null||
                    inputData.getKeyword()=="") {
                //没有定义关键字，是否定义了长度
                int length = inputData.getLen();
                if (length > 3 && length < 8) {
                    //获取到length的值在4-7之间，表示自定义
                    keyword = MyStringUtil.generateRandomKeyword(length);
                    //将对应关系存入数据库
                    Shorted newShorted = new Shorted();
                    newShorted.setKeyword(keyword);
                    newShorted.setLongUrl(longUrl);
                    newShorted.setType(2);
                    newShorted.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    shortedService.insertOne(newShorted);

                    //返回结果
                    ResponseData responseData = ResponseData.createSuccess();
                    responseData.putDataValue("longUrl", longUrl);
                    responseData.putDataValue("shortUrl", host + keyword);
                    responseData.putDataValue("message", "自定义成功");
                    return responseData;
                } else if ((length > 0 && length < 4) || length > 7) {
                    return ResponseData.badCustomLength().putDataValue("ErrMsg", "错误的自定义长度（请在4-7位之间）");
                }


                //不存在 没有自定义 则按默认创建 keyword默认长度为6
                keyword = MyStringUtil.generateRandomKeyword(6);
                //将对应关系存入数据库
                Shorted newShorted = new Shorted();
                newShorted.setKeyword(keyword);
                newShorted.setLongUrl(longUrl);
                newShorted.setType(1);
                newShorted.setCreateTime(new Timestamp(System.currentTimeMillis()));
                shortedService.insertOne(newShorted);

                //返回结果
                ResponseData responseData = ResponseData.createSuccess();
                responseData.putDataValue("longUrl", longUrl);
                responseData.putDataValue("shortUrl", host + keyword);
                return responseData;
            }
            //判断输入的关键字是否合法
            keyword = inputData.getKeyword();
            if (!keyword.matches(MyStringUtil.KEYWORD_REG)) {
                //不合法 返回提示信息
                ResponseData responseData = ResponseData.badCustomKeyword();
                responseData.putDataValue("ErrMsg", "不合法的关键字（需在a-z,A-Z,0-9之间，4到7位");
                return responseData;
            }
            //判断关键字是否已经使用
            String longUrlInDb = shortedService.selectOneSpecifiedByKeyword(keyword);
            if (longUrlInDb != null && longUrlInDb != "") {
                //已被使用，返回提示信息
                ResponseData responseData = ResponseData.badCustomKeyword();
                responseData.putDataValue("ErrMsg", "关键字已被使用");

                return responseData;
            }

            //合法 将对应关系存入数据库
            Shorted newShorted = new Shorted();
            newShorted.setKeyword(keyword);
            newShorted.setLongUrl(longUrl);
            newShorted.setType(2);
            newShorted.setCreateTime(new Timestamp(System.currentTimeMillis()));
            shortedService.insertOne(newShorted);

            //返回结果
            ResponseData responseData = ResponseData.createSuccess();
            responseData.putDataValue("longUrl", longUrl);
            responseData.putDataValue("shortUrl", host + keyword);
            responseData.putDataValue("message", "自定义成功");

            return responseData;
        }
        //存在则返回  自定义在存在的情况下不起作用
        ResponseData responseData = ResponseData.createSuccess();
        responseData.putDataValue("longUrl", longUrl);
        responseData.putDataValue("shortUrl", host + keyword);
        responseData.putDataValue("message", "检测到该网址已记录，自定义失败");

        return responseData;
    }

    @RequestMapping(value = "/v1/query", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData queryLongUrl(@RequestBody Shorted inputData) {
        //1输入判断
        //2在数据库中查询
        //3返回结果

        //1 为空返回错误的关键字
        if (inputData.getKeyword() == null ||
                inputData.getKeyword() == "") {
            return ResponseData.badKeyword().putDataValue("ErrMsg", "获取请求体失败");
        }

        //2 在数据库中查询
        String keyword = inputData.getKeyword();
        String longUrl = shortedService.selectOneSpecifiedByKeyword(keyword);

        //3
        if (longUrl == null ||
                longUrl == "") {
            //为空表示短网址关键字不正确
            ResponseData responseData = ResponseData.badKeyword();
            responseData.putDataValue("shortUrl", host + keyword);

            responseData.putDataValue("ErrMsg", "短网址不存在");
            return responseData;
        }
        //正确返回短网址  长网址  浏览数量
        int viewed = viewedService.selectCountByKeyword(keyword);

        ResponseData responseData = ResponseData.createSuccess();
        responseData.putDataValue("shortUrl", host + keyword);

        responseData.putDataValue("longUrl", longUrl);
        responseData.putDataValue("viewed", viewed);
        return responseData;
    }

    @RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
    public String redirect(@PathVariable("keyword") String keyword) {
        //
        String longUrl = shortedService.selectOneSpecifiedByKeyword(keyword);
        //不存在返回not found
        if (longUrl == null ||
                longUrl == "") {
            return "notfound";
        }

        //存在  计数并并重定向
        Viewed viewed = new Viewed();
        viewed.setKeyword(keyword);
        viewed.setViewTime(new Timestamp(System.currentTimeMillis()));
        viewedService.insertOne(viewed);

        return "redirect:" + longUrl;

    }

}
