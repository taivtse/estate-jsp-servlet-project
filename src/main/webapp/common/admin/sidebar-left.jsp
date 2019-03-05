<!-- start: sidebar -->
<aside id="sidebar-left" class="sidebar-left">

    <div class="sidebar-header">
        <div class="sidebar-title" style="color: #FFF">
            Navigation
        </div>
        <div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html"
             data-fire-event="sidebar-left-toggle">
            <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
        </div>
    </div>

    <div class="nano">
        <div class="nano-content">
            <nav id="menu" class="nav-main" role="navigation">
                <ul class="nav nav-main">
                    <li ${pageContext.request.requestURI eq '/admin' ? 'class="nav-expanded nav-active"':''}>
                        <a href="/">
                            <i class="fa fa-home" aria-hidden="true"></i>
                            <span><spring:message code="label.board"/></span>
                        </a>
                    </li>
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/depart') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/depart'/>">
                            <i class="fa fa-building" aria-hidden="true"></i>
                            <span><spring:message code="label.depart.page.title"/></span>
                        </a>
                    </li>
                </ul>
            </nav>

        </div>

    </div>

</aside>
<!-- end: sidebar -->