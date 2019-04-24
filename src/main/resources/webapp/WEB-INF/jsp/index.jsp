<!DOCTYPE html>
<%@include file="_header.jsp"%>
<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-5cc0383de6815676"></script>
<body>


	<!-- Marketing messaging and featurettes
    ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->
	<h2 class="text-center mt-4 mb-1 h3"></h2>
	<div class="container marketing">
        <h2 class="text-center mb-3 h3">Search Instagram</h2>
        <form action="/search" method="get">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for instagram username or tags" value="" aria-describedby="search-addon" name="query">
                <button class="btn btn-outline-success my-2 my-sm-0" id="search-addon" type="submit">Search </button>
            </div>
        </form>
        <section>
            <h3 class="text-center mt-4 mb-3">Most Popular Instagram Hashtags</h3>
            <ul class="text-center tag-list list-inline mb-5">
                <li class="list-inline-item hashtags">
                    <a href="/tag/instagood">#instagood</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/me">#me</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/nature">#nature</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/instagram">#instagram</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/family">#family</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/beautiful">#beautiful</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/beach">#beach</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/style">#style</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/amazing">#amazing</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/black">#black</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/follow4follow">#follow4follow</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/sunset">#sunset</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/christmas">#christmas</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/l4l">#l4l</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/design">#design</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/winter">#winter</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/selfie">#selfie</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/love">#love</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/fashion">#fashion</a>
                </li>
                <li class="list-inline-item hashtags">
                    <a href="/tag/music">#music</a>
                </li>
            </ul>
        </section>
        <h2 class="text-center mt-4 mb-1 h3">Instagram Popular Users</h2>
        <div class="row">
            <%
                java.util.List<org.bson.Document> list=(java.util.List<org.bson.Document>)request.getAttribute("list");
                for(int i=0;i<list.size();i++){
                    org.bson.Document doc=list.get(i);
            %>
            <div class="col-6 col-xs-12 col-sm-6 col-md-4 col-lg-3 mb-4">
                <div class="card pt-2 border-0">
                    <a href="/kyliejenner" class="pt-2">
                        <img class="rounded-circle img-position" src="image/<%=getString(doc.get("username"))%>.jpg" alt="Kylie Jenner">
                        <div class="card-block text-center">
                            <h4 class="card-title text-truncate mb-0"><%=getString(doc.get("alias"))%></h4>
                        </div>
                    </a>
                </div>
            </div>
            <%
                }
            %>
        </div>


	</div><!-- /.container -->

<%@include file="_footer.jsp"%>
</body>
</html>
