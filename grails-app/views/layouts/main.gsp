<!doctype html>
<html>
<head>
    <title>
        <g:layoutTitle default="Linksharing"/>
    </title>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-default" style="background-color: #5bc0de">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle"
                    data-toggle="collapse" data-target="#myNavbar">
                <span class="glyphicon glyphicon-menu-hamburger"></span>
            </button>
            <a class="navbar-brand" href="#" style="color:black">Link Sharing</a>
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <g:if test="${session.user}">
                    <li><span class="glyphicon glyphicon-comment navbar-glyphsize" data-toggle="modal" data-target="#topicCreate"/></li>
                    <li><span class="glyphicon glyphicon-envelope navbar-glyphsize " data-toggle="modal" data-target="#sendInvitation"/></li>
                    <li><span class="glyphicon glyphicon-link navbar-glyphsize " data-toggle="modal" data-target="#linkCreate"/></li>
                    <li><span class="glyphicon glyphicon-file navbar-glyphsize " data-toggle="modal" data-target="#docCreate"/></li>
                    %{--<button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#sendInvitation">Send Invitation</button>
                    <button type="button"   class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#topicCreate">Create Topic</button>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#linkCreate">Create Link</button>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            data-target="#docCreate">Create Doc</button>--}%
                </g:if>
            %{--<span class="glyphicon glyphicon-file" data-toggle="modal" data-target="#docCreate"></span>--}%


                <span class="dropdown"><g:if test="${session.user}">
                    <span class="dropdown-toggle glyphicon glyphicon-user navbar-glyphsize" id="menu1" data-toggle="dropdown">
                        ${session.user.userName}
                    </span>
                %{-- <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Tutorials
                     <span class="caret"></span></button>--}%
                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                        %{--<li role="presentation"><a role="menuitem" tabindex="-1" href="/user/">Profile</a></li>--}%
                        <li role="presentation"><g:link action="edit" controller="user"
                                                        params='["id": "${session.user.id}"]'>Profile</g:link></li>
                        <g:if test="${session.user.admin}">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="/admin/index">Users</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1"
                                                       href="/admin/showAllTopic">Topics</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1"
                                                       href="/admin/showAllPosts">Posts</a></li>
                            %{--<li role="presentation"><a role="menuitem" tabindex="-1" href="/admin">Users</a></li>
                            --}%%{--<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Users</a></li>--}%%{--
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Topics</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Posts</a></li>--}%
                        </g:if>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="/login/logout">Logout
                        %{--<g:if test="${session.user}">
                            Logout
                        </g:if>
                        <g:else>
                            Sign in
                        </g:else>--}%
                        </a></li>

                    </ul>
                </g:if>
                </span>
            </ul>

            <div class="col-sm-3 col-md-3 pull-right">
                <g:form class="search-form pull-right mySearch">%{--  controller="topic" action="search"--}%
                    <div class="form-group has-feedback">
                        %{--<label class="sr-only">Search</label>--}%
                        <g:hiddenField name="id" value="1" />
                        <g:hiddenField name="max" value="10" />
                        <g:hiddenField name="offset" value="0" />
                        <input style="border-radius: 20px" type="text" class="form-control" name="q" id="q" placeholder="search">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</nav>
<g:if test="${flash.error}">
    <div class="alert alert-danger">
        <strong>${flash.error}</strong>
    </div>
</g:if>
<g:if test="${flash.message}">
    <div class="alert alert-success">
        <strong>${flash.message}</strong>
    </div>
</g:if>
<g:layoutBody/>
<g:render template="/resource/linkCreate" model="[resource: resource]"/>
<g:render template="/resource/docResource" model="[resource: resource]"/>
<g:render template="/topic/create" model="[topic: topic]"/>
<g:render template="/topic/invite"/>

%{--<g:include controller="home" action="showMessage"/>--}%
<asset:javascript src="application.js"/>
%{--<asset:deferredScripts/>--}%
</body>

</html>
