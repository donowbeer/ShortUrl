# ShortUrl

创建短网址API http://119.23.226.102/ShortUrl/v1/create  
method:POST  
Content-Type,application/json  
  
body示例1（默认情况）  
{  
	>"longUrl":"http://www.baidu.com"  
}  
返回结果  
{  
    "code": 11,  
    "message": "Success",  
    "data": {  
        "shortUrl": "http://119.23.226.102/ShortUrl/jMyTux",  
        "longUrl": "http://www.baidu.com"  
    }  
}  
  
body示例2（自定义关键字）  
{  
	"longUrl":"http://taobao.com",  
	"keyword":"taobao"  
}  
返回结果  
{  
    "code": 11,  
    "message": "Success",  
    "data": {  
        "shortUrl": "http://119.23.226.102/ShortUrl/taobao",  
        "longUrl": "http://taobao.com",  
        "message": "自定义成功"  
    }  
}  
  
body示例3（自定义关键字长度）  
{  
	"longUrl":"http://www.taobao.com",  
	"len":5  
}  
返回结果  
{  
    "code": 11,  
    "message": "Success",  
    "data": {  
        "shortUrl": "http://119.23.226.102/ShortUrl/z1Q9W",  
        "longUrl": "http://www.taobao.com",  
        "message": "自定义成功"  
    }  
}  


查询长网址API http://119.23.226.102/ShortUrl/v1/query  
method:POST  
Content-Type,application/json  
  
body示例  
{  
	"keyword":"taobao"  
}  
返回结果  
{  
    "code": 11,  
    "message": "Success",  
    "data": {  
        "shortUrl": "http://119.23.226.102/ShortUrl/taobao",  
        "viewed": 1,  
        "longUrl": "http://taobao.com"  
    }  
}  
viewed即该短网址访问次数  







