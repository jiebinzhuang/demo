<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="UTF-8">
<%@include file="_header.jsp" %>

<body>
<div class="box" id="box">
    <div class="tab" id="tab">
        <span class="current" style="width: 100px">Recommend</span>
        <span style="width: 100px">Fashion</span>
        <span style="width: 100px">Beauty</span>
        <span style="width: 100px">Foods</span>
        <span style="width: 100px">Sports</span>
        <span style="width: 100px">Movie</span>
        <span style="width: 100px">Travel</span>
        <span style="width: 100px">Digital</span>
        <span style="width: 100px">Book</span>
        <span style="width: 100px">Joke</span>
    </div>
    <div class="bd">
        <li class="show">
            <%
                java.util.List<org.bson.Document> list=(java.util.List<org.bson.Document>)request.getAttribute("list");
                for(int i=0;i<list.size()/4;i++){
            %>
            <div class="td1 fl">
                    <%
                int k=i*4;
				for(int m=0;m<4;m++){
					org.bson.Document doc=list.get(k+m);
			%>
                    <div>
                        <div class="td1-<%=m+1%> fl">
                            <img src="<%=getString(doc.get("preview_img"))%>" class="lazy"
                                 xsrc="../images/c41aa233-59f7-554f-a6e2-ce47d9a43155.jpg">
                            <%--<p>讲真，如果你不看到最后，你知道这个是什么吗？</p>--%>
                            <div class="di1-<%=m+1%>">
                                <img src="../image/user/<%=getString(doc.get("username"))%>.jpg" class="lazy">
                                <p><%=getString(doc.get("username"))%></p>
                            </div>
                            <a href="点击跳转画面.html" class="mask1-<%=m+1%>"></a>
                        </div>
                    </div>
                <%
                    }
                %>
            </div><!-- /.row -->
            <%
                }
            %>
        </li>
        <li>
            <div class="td1 fl">
                <div>
                    <div class="td1-1 fl">
                        <img src="../images/krystal.jpg" class="lazy" xsrc="../images/krystal.jpg">
                        <p>好久不见👓这次来介绍我的眼镜👓我有近视</p>
                        <div class="di1-1">
                            <img src="../images/krystal18.jpg" class="lazy">
                            <p>zhowei</p>
                        </div>
                        <a href="" class="mask1-1"></a>
                    </div>
                </div>
                <div>
                    <div class="td1-2">
                        <img src="../images/krystal2.jpg" class="lazy">
                        <p>【H&M穿搭分享】一衣多穿 这个秋天一定要pick的气质毛衣</p>
                        <div class="di1-2">
                            <img src="../images/krystal20.jpg" class="lazy">
                            <p>一口奶油Cherry</p>
                        </div>
                        <a href="" class="mask1-2"></a>
                    </div>
                </div>
                <div>
                    <div class="td1-3">
                        <img src="../images/krystal3.jpg" class="lazy">
                        <p>干货|这6种情况戴眼镜比不戴好看,男女适用!看完你可能要换</p>
                        <div class="di1-3">
                            <img src="../images/krystal19.jpg" class="lazy">
                            <p>鹿先生</p>
                        </div>
                        <a href="" class="mask1-3"></a>
                    </div>
                </div>
                <div>
                    <div class="td1-4">
                        <img src="../images/krystal5.jpg" class="lazy">
                        <p>OOTD|假期当个酷女孩🆒两套超吸睛的秋季穿搭分享!国庆</p>
                        <div class="di1-4">
                            <img src="../images/krystal13.jpeg" class="lazy">
                            <p>Nexxs</p>
                        </div>
                        <a href="" class="mask1-4"></a>
                    </div>
                </div>
            </div>
            <div class="td2 fl">
                <div class="td2-1">
                    <img src="../images/krystal13.jpeg" class="lazy">
                    <p>OOTD|假期当个酷女孩🆒两套超吸睛的秋季穿搭分享!国庆</p>
                    <div class="di2-1">
                        <img src="../images/5ae6be77b46c5d6f55fc73b8.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                    <a href="" class="mask2-1"></a>
                </div>
                <div class="td2-2">
                    <img src="../images/krystal14.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di2-2">
                        <img src="../images/5ae9aa7cd2c8a508942d9e28.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>hkgfjkglg</p>
                    </div>
                    <a href="" class="mask2-2"></a>
                </div>
                <div class="td2-3">
                    <img src="../images/krystal17.jpg" class="lazy">
                    <p>干货|这6种情况戴眼镜比不戴好看,男女适用!看完你可能要换</p>
                    <div class="di2-3">
                        <img src="../images/krystal.jpg" class="lazy">
                        <p>鹿先生</p>
                    </div>
                    <a href="" class="mask2-3"></a>
                </div>
                <div class="td2-4">
                    <img src="../images/krystal15.jpeg" class="lazy">
                    <p>【H&M穿搭分享】一衣多穿 这个秋天一定要pick的气质毛衣</p>
                    <div class="di2-4">
                        <img src="../images/krystal17.jpg" class="lazy">
                        <p>一口奶油</p>
                    </div>
                    <a href="" class="mask2-4"></a>
                </div>
            </div>
            <div class="td3 fl">
                <div class="td3-1">
                    <img src="../images/680dc843-047e-4d92-aeb2-d4c4de524011.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di3-1">
                        <img src="../images/5ae995c914de416bebbeb2c8.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td3-2">
                    <img src="../images/5ba4c95f-612c-5e2f-bce8-869d786e970d.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di3-2">
                        <img src="../images/5bb55b63c8cd69000146245d.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td3-3">
                    <img src="../images/78d838ce-0ebf-53de-819c-3e153b6c93d7.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di3-3">
                        <img src="../images/5baa1e267727d70001c6edee.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td3-4">
                    <img src="../images/aacc2641-5fa7-56d7-860d-e09a2c29ba49.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di3-4">
                        <img src="../images/5bba296d87e4370001838597.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
            </div>
            <div class="td4 fl">
                <div class="td4-1">
                    <img src="../images/c41aa233-59f7-554f-a6e2-ce47d9a43155.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di4-1">
                        <img src="../images/5bb445d1553c580001275c3f.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td4-2">
                    <img src="../images/5ba4c95f-612c-5e2f-bce8-869d786e970d.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di4-2">
                        <img src="../images/5afa5a69d2c8a508918b4d46.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td4-3">
                    <img src="../images/78d838ce-0ebf-53de-819c-3e153b6c93d7.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di4-3">
                        <img src="../images/5af7d215d2c8a571862d9d0e.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td4-4">
                    <img src="../images/aacc2641-5fa7-56d7-860d-e09a2c29ba49.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di4-4">
                        <img src="../images/5b9e4dada98b320001425102.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
            </div>
            <div class="td5 fl">
                <div class="td5-1">
                    <img src="../images/c41aa233-59f7-554f-a6e2-ce47d9a43155.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di5-1">
                        <img src="../images/5b45d50fd2c8a54dfe8d2faf.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td5-2">
                    <img src="../images/5ba4c95f-612c-5e2f-bce8-869d786e970d.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di5-2">
                        <img src="../images/5b21afcb14de415308df21ac.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td5-3">
                    <img src="../images/78d838ce-0ebf-53de-819c-3e153b6c93d7.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di5-3">
                        <img src="../images/5b289fe2b46c5d17ce4f5988.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td5-4">
                    <img src="../images/aacc2641-5fa7-56d7-860d-e09a2c29ba49.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di5-4">
                        <img src="../images/5b739a6be8ac2b20718ab151.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
            </div>
            <div class="td6 fl">
                <div class="td6-1">
                    <img src="../images/c41aa233-59f7-554f-a6e2-ce47d9a43155.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di6-1">
                        <img src="../images/5a75da4914de410d806aa0e0.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td6-2">
                    <img src="../images/5ba4c95f-612c-5e2f-bce8-869d786e970d.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di6-2">
                        <img src="../images/5a54a388b46c5d0aaf6ede59.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td6-3">
                    <img src="../images/78d838ce-0ebf-53de-819c-3e153b6c93d7.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di6-3">
                        <img src="../images/5aee9d9ab46c5d219dc21aa8.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td6-4">
                    <img src="../images/aacc2641-5fa7-56d7-860d-e09a2c29ba49.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di6-4">
                        <img src="../images/5b8557bd45868b000179d5ba.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
            </div>
            <div class="login-header">
                <a id="link" href="javascript:void(1);">查看更多</a>
            </div>
            <div id="login" class="login">
                <img src="../images/cute.png" class="img1">
                <img src="../images/326e7c5d-5e1f-4a3f-8a96-86f676e3a856.png" class="img2">
                <p class="p1">扫码下载小红书app</p>
                <p class="p2">与全世界的小红薯一起标记生活</p>
                <a href="" class="sure" id="sure">确定</a>
            </div>
            <!-- 遮盖层 -->
            <div id="bg" class="login-bg"></div>
        </li>
        <li>
            <div class="td1 fl">
                <div>
                    <div class="td1-1 fl">
                        <img src="../images/郑秀晶呐.jpg" class="lazy"
                             xsrc="../images/c41aa233-59f7-554f-a6e2-ce47d9a43155.jpg">
                        <p>好久不见👓这次来介绍我的眼镜👓我有近视</p>
                        <div class="di1-1">
                            <img src="../images/krystal10.jpg" class="lazy">
                            <p>zhowei</p>
                        </div>
                        <a href="" class="mask1-1"></a>
                    </div>
                </div>
                <div>
                    <div class="td1-2">
                        <img src="../images/krystal10.jpg" class="lazy">
                        <p>【H&M穿搭分享】一衣多穿 这个秋天一定要pick的气质毛衣</p>
                        <div class="di1-2">
                            <img src="../images/krystal3.jpg" class="lazy">
                            <p>一口奶油Cherry</p>
                        </div>
                        <a href="" class="mask1-2"></a>
                    </div>
                </div>
                <div>
                    <div class="td1-3">
                        <img src="../images/krystal11.jpeg" class="lazy">
                        <p>干货|这6种情况戴眼镜比不戴好看,男女适用!看完你可能要换</p>
                        <div class="di1-3">
                            <img src="../images/krystal.jpg" class="lazy">
                            <p>鹿先生</p>
                        </div>
                        <a href="" class="mask1-3"></a>
                    </div>
                </div>
                <div>
                    <div class="td1-4">
                        <img src="../images/krystal2.jpg" class="lazy">
                        <p>OOTD|假期当个酷女孩🆒两套超吸睛的秋季穿搭分享!国庆</p>
                        <div class="di1-4">
                            <img src="../images/krystal20.jpg" class="lazy">
                            <p>Nexxs</p>
                        </div>
                        <a href="" class="mask1-4"></a>
                    </div>
                </div>
            </div>
            <div class="td2 fl">
                <div class="td2-1">
                    <img src="../images/krystal18.jpg" class="lazy">
                    <p>OOTD|假期当个酷女孩🆒两套超吸睛的秋季穿搭分享!国庆</p>
                    <div class="di2-1">
                        <img src="../images/krystal19.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                    <a href="" class="mask2-1"></a>
                </div>
                <div class="td2-2">
                    <img src="../images/krystal19.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di2-2">
                        <img src="../images/5ae9aa7cd2c8a508942d9e28.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>hkgfjkglg</p>
                    </div>
                    <a href="" class="mask2-2"></a>
                </div>
                <div class="td2-3">
                    <img src="../images/krystal20.jpg" class="lazy">
                    <p>干货|这6种情况戴眼镜比不戴好看,男女适用!看完你可能要换</p>
                    <div class="di2-3">
                        <img src="../images/5b8557bd45868b000179d5ba.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>鹿先生</p>
                    </div>
                    <a href="" class="mask2-3"></a>
                </div>
                <div class="td2-4">
                    <img src="../images/krystal5.jpg" class="lazy">
                    <p>【H&M穿搭分享】一衣多穿 这个秋天一定要pick的气质毛衣</p>
                    <div class="di2-4">
                        <img src="../images/5b61801614de412d2cebf974.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>一口奶油</p>
                    </div>
                    <a href="" class="mask2-4"></a>
                </div>
            </div>
            <div class="td3 fl">
                <div class="td3-1">
                    <img src="../images/680dc843-047e-4d92-aeb2-d4c4de524011.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di3-1">
                        <img src="../images/5ae995c914de416bebbeb2c8.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td3-2">
                    <img src="../images/5ba4c95f-612c-5e2f-bce8-869d786e970d.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di3-2">
                        <img src="../images/5bb55b63c8cd69000146245d.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td3-3">
                    <img src="../images/78d838ce-0ebf-53de-819c-3e153b6c93d7.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di3-3">
                        <img src="../images/5baa1e267727d70001c6edee.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td3-4">
                    <img src="../images/aacc2641-5fa7-56d7-860d-e09a2c29ba49.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di3-4">
                        <img src="../images/5bba296d87e4370001838597.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
            </div>
            <div class="td4 fl">
                <div class="td4-1">
                    <img src="../images/c41aa233-59f7-554f-a6e2-ce47d9a43155.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di4-1">
                        <img src="../images/5bb445d1553c580001275c3f.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td4-2">
                    <img src="../images/5ba4c95f-612c-5e2f-bce8-869d786e970d.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di4-2">
                        <img src="../images/5afa5a69d2c8a508918b4d46.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td4-3">
                    <img src="../images/78d838ce-0ebf-53de-819c-3e153b6c93d7.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di4-3">
                        <img src="../images/5af7d215d2c8a571862d9d0e.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td4-4">
                    <img src="../images/aacc2641-5fa7-56d7-860d-e09a2c29ba49.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di4-4">
                        <img src="../images/5b9e4dada98b320001425102.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
            </div>
            <div class="td5 fl">
                <div class="td5-1">
                    <img src="../images/c41aa233-59f7-554f-a6e2-ce47d9a43155.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di5-1">
                        <img src="../images/5b45d50fd2c8a54dfe8d2faf.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td5-2">
                    <img src="../images/5ba4c95f-612c-5e2f-bce8-869d786e970d.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di5-2">
                        <img src="../images/5b21afcb14de415308df21ac.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td5-3">
                    <img src="../images/78d838ce-0ebf-53de-819c-3e153b6c93d7.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di5-3">
                        <img src="../images/5b289fe2b46c5d17ce4f5988.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td5-4">
                    <img src="../images/aacc2641-5fa7-56d7-860d-e09a2c29ba49.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di5-4">
                        <img src="../images/5b739a6be8ac2b20718ab151.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
            </div>
            <div class="td6 fl">
                <div class="td6-1">
                    <img src="../images/c41aa233-59f7-554f-a6e2-ce47d9a43155.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di6-1">
                        <img src="../images/5a75da4914de410d806aa0e0.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td6-2">
                    <img src="../images/5ba4c95f-612c-5e2f-bce8-869d786e970d.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di6-2">
                        <img src="../images/5a54a388b46c5d0aaf6ede59.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td6-3">
                    <img src="../images/78d838ce-0ebf-53de-819c-3e153b6c93d7.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di6-3">
                        <img src="../images/5aee9d9ab46c5d219dc21aa8.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
                <div class="td6-4">
                    <img src="../images/aacc2641-5fa7-56d7-860d-e09a2c29ba49.jpg" class="lazy">
                    <p>身高不到一米六的我，胆子是有够肥的，买40号的衣服，背big</p>
                    <div class="di6-4">
                        <img src="../images/5b8557bd45868b000179d5ba.jpg@80w_80h_90q_1e_1c_1x.jpg" class="lazy">
                        <p>zhowei</p>
                    </div>
                </div>
            </div>
            <div class="login-header">
                <a id="link" href="javascript:void(0);">查看更多</a>
            </div>
            <div id="login" class="login">
                <img src="../images/cute.png" class="img1">
                <img src="../images/326e7c5d-5e1f-4a3f-8a96-86f676e3a856.png" class="img2">
                <p class="p1">扫码下载小红书app</p>
                <p class="p2">与全世界的小红薯一起标记生活</p>
                <a href="" class="sure" id="sure">确定</a>
            </div>
            <!-- 遮盖层 -->
            <div id="bg" class="login-bg"></div>
        </li>
        <li>我是美食模块</li>
        <li>我是运动</li>
        <li>我是影音模块</li>
        <li>我是旅行模块</li>
        <li>我是居家模块</li>
        <li>我是母婴模块</li>
        <li>我是读书模块</li>
        <li>我是数码模块</li>
        <li>我是时尚男士模块</li>
        <li>我是医药模块</li>
        <li>我是动漫模块</li>
    </div>
</div>
</body>
</html>

