<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>日期时间选择控件</title>
    <style type="text/css">
    * {
        margin: 0;
        padding: 0;
        -webkit-appearance: none; //去掉浏览器默认样式
        -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
        -webkit-touch-callout: none;
        box-sizing: border-box;
    }
    
    html,
    body {
        margin: 0 auto;
        width: 100%;
        min-height: 100%;
        overflow-x: hidden;
        -webkit-user-select: none;
    }
    
    body {
        font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
        -webkit-text-size-adjust: 100%; //关闭自动调整字体
        -webkit-overflow-scrolling: touch;
        overflow-scrolling: touch;
    }
    
    input {
        width: 90%;
        height: 40px;
        font-size: 18px;
        border: 1px solid #b72f20;
        border-radius: 5px;
        margin: 20px 5% 0 5%;
        padding: 5px;
    }
    
    h1 {
        background-color: #b72f20;
        color: #fff;
        font-size: 25px;
        text-align: center;
        padding: 10px;
    }
    </style>
    <link rel="stylesheet" href="../../static/style/LCalendar.css">
</head>

<body>
    <div>
        <h1>LCalendar移动端日期时间选择</h1>
        <div>
            <input id="demo1" type="text" readonly="" placeholder="日期选择特效" data-lcalendar="2016-05-11,2017-05-11" />
        </div>
        <div>
            <input id="demo2" type="text" readonly="" placeholder="日期和时间选择特效" value="2016-05-11 17:44" />
        </div>
        <div>
            <input id="demo3" type="text" readonly="" placeholder="时间选择特效" />
        </div>
        <div>
            <input id="demo4" type="text" readonly="" placeholder="年月选择特效" />
        </div>
        
        <input id="date" name="dateName" defaultValue="2017年09月02" min="2017-07-20" max="2017-09-23"  type="date"/>
        <!-- <div class="content-block">
            <input type="datetime-local" />
        </div> -->
        <input type="datetime-local" value="2018-08-20">
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
        <div>
        	-------------
        </div>
    </div>
    <!--demo-content-->
    <script src="../../static/script/LCalendar.js"></script>
    <script>
    var calendar = new LCalendar();
    calendar.init({
        'trigger': '#demo1', //标签id
        'type': 'date', //date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择,
        'minDate': '2000-1-1', //最小日期
        'maxDate': '2017-10-10' //new Date().getFullYear() + '-' + (new Date().getMonth() + 1) + '-' + new Date().getDate() //最大日期
    });
    var calendardatetime = new LCalendar();
    calendardatetime.init({
        'trigger': '#demo2',
        'type': 'datetime'
    });
    var calendartime = new LCalendar();
    calendartime.init({
        'trigger': '#demo3',
        'type': 'time'
    });
    var calendarym = new LCalendar();
    calendarym.init({
        'trigger': '#demo4',
        'type': 'ym',
        'minDate': '2017-01',
        'maxDate': new Date().getFullYear() + '-' + (new Date().getMonth() + 1)
    });
    </script>
</body>

</html>

