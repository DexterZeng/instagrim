<%-- 
    Document   : SamplePics
    Created on : 2015-10-21, 4:11:51
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            div.img
              {
              position: relative;
              top: 30px;
              left: 300px;
              margin:3px;
              border:1px solid #bebebe;
              height:auto;
              width:auto;
              float:left;
              text-align:center;
              }
            div.img img
              {
              display:inline;
              margin:3px;
              border:1px solid #bebebe;
              }
            div.img a:hover img
              {
              border:1px solid #333333;
              }
            div.desc
              {
              text-align:center;
              font-weight:normal;
              width:150px;
              font-size:12px;
              margin:10px 5px 10px 5px;
              }
            h2 {background-color:black;
                    color: white;
                    font-variant: small-caps;
                    display:block;
                    font-weight:bold;
                    width:400px;
                    text-align:center;
                    padding:4px;
                    text-decoration:none;  
                    }
            h1 {font-size: 300%;
                    font-style: italic;
                    font-variant: small-caps;}
            body
                {
                    background-image: url(http://www.technosamrat.com/wp-content/uploads/2012/05/Black-and-White-Wallpaper-Picture.jpg);
                    background-position: center;
                    background-repeat: no-repeat;
                    background-position: 0px -50px;
                } 
            nav a:link,nav a:visited
                {
                display:block;
                font-weight:bold;
                color:white;
                background-color:#bebebe;
                width:120px;
                text-align:center;
                padding:4px;
                text-decoration:none;
                text-transform:uppercase;
                }
            nav a:hover,nav a:active
                {
                background-color:black;
                }
            ul{
                list-style-type:none;
                margin:0;
                padding:0;
                }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css"  />
    </head>
    <body>
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        <nav>
            <ul>
                <li class="nav"><a href="upload.jsp">Upload</a></li>
                <li class="nav"><a href="TransToInd.jsp">Home</a></li>
            </ul>
        </nav>
        <div class="img">
            <a target="_blank" href="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/07/black-and-white-wallpaper-2.jpg">
            <img src="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/07/black-and-white-wallpaper-2.jpg" width="160" height="160">
            </a>
        </div>

      <div class="img">
            <a target="_blank" href="http://img03.deviantart.net/4810/i/2007/115/b/1/black_by_habter.jpg">
            <img src="http://img03.deviantart.net/4810/i/2007/115/b/1/black_by_habter.jpg"  width="160" height="160">
            </a>
      </div>

      <div class="img">
            <a target="_blank" href="http://farm1.static.flickr.com/155/345998663_11c767584c.jpg">
            <img src="http://farm1.static.flickr.com/155/345998663_11c767584c.jpg"  width="160" height="160">
            </a>
      </div>

      <div class="img">
            <a target="_blank" href="http://farm1.static.flickr.com/160/345997850_866d60d68c.jpg">
            <img src="http://farm1.static.flickr.com/160/345997850_866d60d68c.jpg"  width="160" height="160">
            </a>
      </div>
    </body>
</html>