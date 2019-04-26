<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="UTF-8">
<%@include file="include.jsp" %>

    <script src="../js/jquery-1.8.2.js"></script>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery-1.11.0.min.js"></script>
    <script src="../js/zoom-slideshow.js"></script>
    <script src="../js/postdetail.js"></script>
    <link rel="stylesheet" href="../css/base.css">
    <link rel="stylesheet" href="../css/footer.css">
    <link rel="stylesheet" href="../css/header.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/normalize.css">
    <link rel="stylesheet" href="../css/postdetail.css">

<%@include file="_header.jsp" %>
<body>
<%
     org.bson.Document doc=(org.bson.Document)request.getAttribute("postdoc");
    String[] xx= (String[]) doc.get("preview_img");

%>
<div class="card-note" id="card-note">
    <div class="left-card">
        <div id="wrapper">
            <div id="content">
                <!--大图预览区-->
                <div id="view">
                    <!--左箭头导航-->
                    <div id="nav-left-thumbs"><</div>
                    <img src="<%=getString(doc.get("preview_img"))%>" style="width: 100%; height: 100%"  alt="" />
                    <!--右箭头导航-->
                    <div id="nav-right-thumbs">></div>
                </div>
                <!--缩略图-->
                <div id="thumbs">
                    <div id="pics-thumbs">
                        <img src="<%=getString(doc.get("preview_img"))%>" alt="Nature1" />
                        <img src="<%=getString(doc.get("preview_img"))%>" alt="Nature1" />
                    </div>
                </div>
            </div>

        </div>
        <div class="note-top">
            <div class="related-tags">
                <a href="" class="each-tag" target="_blank">蛋炒饭</a>
                <a href="" class="each-tag" target="_blank">炒饭</a>
                <a href="" class="each-tag" target="_blank">美食</a>
                <a href="" class="each-tag" target="_blank">广州</a>
                <a href="" class="each-tag" target="_blank">拌饭</a>
            </div>
        </div>
        <div class="content">
            <h1 class="as-p">讲真，如果你不看到最后，你知道这个是什么吗？</h1>
            <p>讲真，如果你不看到最后，你知道这个是什么吗？</p>

            <p>前几天做了个蛋炒饭。婆婆说好好吃，怎么会有从前的味道。我猜她回忆里的味道应该是猪油拌饭！猪油拌饭在我们广州或者香港叫猪油捞饭，
                美食家蔡澜老先生，用将它列入“死前必吃清单”这种实际行动，可见他对于猪油饭的热爱！和婆婆年龄相仿的老人家估计也吃过吧。</p>

            <p>记忆里的猪油拌饭，挖一小块猪油，借着热腾腾的米饭化开，香口惹，再有几颗油渣简直完美~</p>

            <p>除了做月饼，酥饼，我也很少熬制猪油了。这些年猪油被黑化得不成样子，但它的好，估计也只有有回忆的人记得。</p>

            <p>在这样这个惬意的秋日午后，听一首小曲，慢慢熬制一碗猪油～这样的感觉，好像回到了小时候。那个吃什么都觉得美味，
                吃什么都心满意足的小时候。😇</p>

            <p>🍑🍑🍑</p>

            <p>🍃食材🍃</p>

            <p>猪板油500g，清水100g,盐少量</p>

            🍃做法🍃</p>

            <p>1⃣猪板油用热水洗干净，然后切成小块。</p>

            <p>2⃣取一口锅，倒入猪板油和清水。</p>

            <p>3⃣中火慢慢炒干，待有油出来，转小火。</p>

            <p>4⃣加入少量的盐。慢慢煮，直到猪板油变成干油渣。炸透。</p>

            <p>5⃣过滤掉油渣，把油倒入碗中放凉即可。</p>

            <p>🍑🍑🍑</p>

            <p>说真的，猪油渣小时候真的吃过，炒豆角，炒青菜，都特别香。现在真的越来越少看到了～</p>
            <p><a href="" class="mention">@吃货薯</a></p>
            <p><a href="" class="hash-tag">#美食</a></p>
            <p><a href="" class="hash-tag">#我的美食日记</a></p>
            <p><a href="" class="hash-tag">#超级下饭的家常菜</a></p>
            <p><a href="" class="hash-tag">#吃货日常</a></p>
            <p><a href="" class="hash-tag">#我是吃货</a></p>
            <p><a href="" class="hash-tag">#小时候的味道</a></p>
        </div>
        <div class="tags">
            <div class="left-tag">
                <span class="title">发布于</span>
                <span class="time">2018-10-12 14:16</span>
            </div>
            <div class="right-tag">
                <span class="like">
                    <i></i>
                    <span>353</span>
                </span>
                <span class="comment">
                    <i></i>
                    <span>200</span>
                </span>
                <span class="star">
                    <i></i>
                    <span>293</span>
                </span>
            </div>
        </div>
        <div class="bottom-info">
            <a href="" class="author-info">
                <div class="left">
                    <div class="left-img">
                        <img src="../images/5b0f234ed2c8a574a12ff25b.jpg@80w_80h_90q_1e_1c_1x.jpg" style="width: 30px;height: 30px;"  alt="">
                        <i class="border-img" style="width: 30px;height: 30px"></i>
                    </div>
                </div>
                <div class="right" style="width: 82px">
                    <h6 class="name-bottom" style="font-size: 14px;line-height: 25px">小饭粒funi</h6>
                </div>
            </a>
            <div class="share-item">
                <span class="apart-border"></span>
                <p class="title">一起来分享给朋友们看看吧：</p>
                <div class="all-icon">
                    <a href="" class="wx"></a>
                    <a href="" class="wb"></a>
                    <a href="" class="qq"></a>
                </div>
            </div>
            <div class="codeshow" style="display: none">
                <img src="../images/wxewm.png" alt="">
                <p>分享至微信</p>
            </div>
        </div>
        <div class="alltip">
            <h3 class="top">
                <i class="remain"></i>
                <span class="brief">笔记评论</span>
            </h3>
            <div class="content">
                <div class="comment">
                    <div class="comment-info">
                        <div class="user">
                            <a href="">
                                <img src="../images/57a29c255e87e7065f186579.jpg@80w_80h_90q_1e_1c_1x.jpg" alt="" class="avatar">
                            </a>
                            <div class="user-info">
                                <a href="" class="user-name">贪吃鬼</a>
                                <span class="ptime">2018-10-13 04:36</span>
                            </div>
                        </div>
                        <div class="like">
                            <i class="icon">
                                <img src="../images/heart.png" alt="" style="width:20px;height: 20px">
                            </i>
                            <span class="zan">8</span>
                        </div>
                    </div>
                    <p class="content">猪油很好的对心脏还是肝好的，反正是对身体好的，只是这些年被黑的惨</p>
                    <div class="replies">
                    <span class="replier">
                        小饭粒funni
                        <span class="author">
                            (作者)
                        </span>
                        :
                    </span>
                        <span class="replycontent">
                        我也觉得是，有点销声匿迹。
                    </span>
                    </div>
                </div>
                <div class="comment">
                    <div class="comment-info">
                        <div class="user">
                            <a href="">
                                <img src="../images/5aeb0fafd2c8a50fe88b4c89.jpg@80w_80h_90q_1e_1c_1x.jpg" alt="" class="avatar">
                            </a>
                            <div class="user-info">
                                <a href="" class="user-name">阿末的美食日记</a>
                                <span class="ptime">2018-10-12 15:10</span>
                            </div>
                        </div>
                        <div class="like">
                            <i class="icon">
                                <img src="../images/heart.png" alt="" style="width:20px;height: 20px">
                            </i>
                            <span class="zan">4</span>
                        </div>
                    </div>
                    <p class="content">我家经常吃猪油</p>
                    <div class="replies">
                    <span class="replier">
                        小饭粒funni
                        <span class="author">
                            (作者)
                        </span>
                        :
                    </span>
                        <span class="replycontent">
                        [害羞R]我家老公不让吃。'
                    </span>
                    </div>
                </div>
                <div class="bottom"></div>
            </div>
            <div style="display: none"></div>
        </div>
    </div>
    <div class="right-card">
        <div class="author-item bottom-gap">
            <h3 class="title">笔记作者</h3>
            <a href="" class="author-info">
                <div class="left">
                    <div class="left-img">
                        <img src="../images/5b0f234ed2c8a574a12ff25b.jpg@80w_80h_90q_1e_1c_1x.jpg" style="width: 50px;height: 50px" alt="">
                        <i class="border-img" style="width: 50px;height:50px;"></i>
                    </div>
                </div>
                <div class="right" style="width: 190px">
                    <h6 class="name" style="font-size:16px;">
                        <span class="name-detail">小饭粒funni</span>
                        <i class="level-detail" style="background-image: url('../images/f11_v2.png')"></i>
                    </h6>
                    <p class="brief" style="font-size:14px;">暂时还没有个性签名哦~</p>
                </div>
            </a>
            <div class="card-info">
                <div class="inner">
                    <span>笔记</span>
                    <span class="note">418</span>
                </div>
                <div class="inner">
                    <span>粉丝</span>
                    <span class="fans">93.9万</span>
                </div>
                <div class="inner">
                    <span>获赞与收藏</span>
                    <span class="collect">93.8万</span>
                </div>
            </div>
        </div>
        <div class="bottom gap" id="bottom">
            <div class="panel-card">
                <h3>相关笔记</h3>
                <a href="" class="inner">
                    <div class="picture">
                        <span class="photo" style="background-image: url(../images/19616c1e-2117-5d1a-ac2d-7606e6cbd7e4.jpg)"></span>
                        <i class="normal"></i>
                    </div>
                    <div class="content">
                        <p class="desc">
                            不想吃肉的时候来一份醋溜大白菜粉条🐒爽滑美味超级开胃
                        </p>
                        <div class="info">
                            <img src="../images/heart.png"style="width: 20px;height: 20px" alt="">
                            <span class="counts">1087</span>
                        </div>
                    </div>
                </a>
                <a href="" class="inner">
                    <div class="picture">
                        <span class="photo" style="background-image: url(../images/388e7d08-f9c7-57c6-9089-38aca52f348d.jpg)"></span>
                        <i class="normal"></i>
                    </div>
                    <div class="content">
                        <p class="desc">
                            每天吃都不会腻的番茄肉末豆腐😋你要是做了这一锅，保证
                        </p>
                        <div class="info">
                            <img src="../images/heart.png" style="width: 20px;height: 20px" alt="">
                            <span class="counts">526</span>
                        </div>
                    </div>
                </a>
                <a href="" class="inner">
                    <div class="picture">
                        <span class="photo" style="background-image: url(../images/e7edeeb2-e975-590d-9f0d-9e5ee6a56d5b.jpg)"></span>
                        <i class="normal"></i>
                    </div>
                    <div class="content">
                        <p class="desc">
                            不加一滴水的肉末蒜苔！别着急流口水！赶紧去盛一大碗米
                        </p>
                        <div class="info">
                            <img src="../images/heart.png"  style="width: 20px;height: 20px" alt="">
                            <span class="counts">818</span>
                        </div>
                    </div>
                </a>
                <a href="" class="inner">
                    <div class="picture">
                        <span class="photo" style="background-image: url(../images/085ec3cc-deed-57d4-aa65-fb888b71bd73.jpg)"></span>
                        <i class="normal"></i>
                    </div>
                    <div class="content">
                        <p class="desc">
                            从小吃到大的酱油炒饭，满满的回忆，香到没朋友 孙大爷小
                        </p>
                        <div class="info">
                            <img src="../images/heart.png" style="width: 20px;height: 20px" alt="">
                            <span class="counts">514</span>
                        </div>
                    </div>
                </a>
                <div class="more">查看更多</div>
            </div>
        </div>

    </div>
    <div class="load" style="display: none">
        <img src="../images/loading.7277f6d.gif" alt="">
    </div>

    <div class="bottom-fixed bottom-info" id="bottom-fixed">
        <a href="" class="author-info">
            <div class="left">
                <div class="left-img">
                    <img src="../images/5b0f234ed2c8a574a12ff25b.jpg@80w_80h_90q_1e_1c_1x.jpg" style="width: 30px;height: 30px;"  alt="">
                    <i class="border-img" style="width: 30px;height: 30px"></i>
                </div>
            </div>
            <div class="right" style="width: 82px">
                <h6 class="name-bottom" style="font-size: 14px;line-height: 25px">小饭粒funi</h6>
            </div>
        </a>
        <div class="share-item">
            <span class="apart-border"></span>
            <p class="title">一起来分享给朋友们看看吧：</p>
            <div class="all-icon">
                <a href="" class="wx"></a>
                <a href="" class="wb"></a>
                <a href="" class="qq"></a>
            </div>
        </div>
        <div class="codeshow" style="display: none">
            <img src="../images/wxewm.png" alt="">
            <p>分享至微信</p>
        </div>
    </div>

</div>
<!--回到顶部的制作-->
<div class="backtop" style="display:none;">
</div>

<!--二维码的制作-->
<div class="ewm">
    <div class="smallewm"></div>
    <div class="bigewm" style="display:none;">
        <img src="../images/936da4bf-1faa-4e6a-af2d-9b853c3e6e55.png" alt="" class="ewmimg">
        <p class="title">扫我下载APP</p>
    </div>
</div>
</body>
<!--//鼠标移入显示二维码-->
<!--回到顶部-->
<script>
    var smallewm=document.querySelectorAll(".smallewm")[0];
    var bigewm=document.querySelectorAll(".bigewm")[0];
    smallewm.onmouseenter=function (){
        bigewm.style.display="block"
    }
    smallewm.onmouseleave=function () {
        bigewm.style.display="none"
    }

    //回到顶部
    var header=document.querySelectorAll(".header")[0];
    var backtop=document.querySelectorAll(".backtop")[0];
    header.className="header"
    window.onscroll=function (e) {
        var oev=e||window.event;
        var scrollTop=getScroll().scrollTop;
        if(scrollTop>10){
            header.className="header";
            backtop.style.display="block"
            backtop.onmouseover=function () {
                backtop.className="scrolltop"
            }
            backtop.onmouseleave=function (){
                backtop.className="backtop"
            }
        }
        else{
            header.className="header"
            backtop.style.display="none"
        }
    }
    backtop.onclick=function () {
        var timer=setInterval(function () {
            var currentScrollTop=getScroll().scrollTop;
            var targetScrollTop=0;
            var step=-20;
            if(Math.abs(currentScrollTop-targetScrollTop)<Math.abs(step)){
                document.body.scrollTop=targetScrollTop;
                document.documentElement.scrollTop=targetScrollTop;
                clearInterval(timer);
                return
            }
            currentScrollTop+=step;
            document.body.scrollTop=currentScrollTop;
            document.documentElement.scrollTop=currentScrollTop;
        },1)
    }
</script>
</html>