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
                    <li ${fn:startsWith(pageContext.request.requestURI, '/admin/building/list') ? 'class="nav-expanded nav-active"':''}>
                        <a href="<c:url value='/admin/building/list'/>">
                            <i class="fa fa-building" aria-hidden="true"></i>
                            <span>Quản lý toà nhà</span>
                        </a>
                    </li>
                </ul>
            </nav>

        </div>

    </div>

</aside>
<!-- end: sidebar -->