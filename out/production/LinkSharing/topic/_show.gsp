<%@ page import="com.ttnd.linksharing.util.Seriousness" %>
<!-- <div class="row"> -->
<g:each in="${topics}" var="topic">
    <div class="well">
        <div class="row">

            <div class="col-sm-2">
                %{--<div class="well">--}%
                <pic:userImage id="${topic.createdBy.id}"/>
                <!--<span class="glyphicon glyphicon-user "></span>-->
                <!-- <img src="../images/user_image.jpg"/> -->
            </div>

            <div class="col-sm-10 ">
                <div>
                    <g:link action="show" controller="topic" params='["id": "${topic.id}"]'>${topic.name}</g:link>
                    <span class="text-muted">
                        (${topic.visibility})
                    </span>
                    %{--<a href="/topicShow/${topic.id}" class=""></a>--}%
                </div>

                <div>
                    <div style="padding-left: 0px" class="col-md-5">
                        <div>${topic.createdBy}</div>

                        <div><a>subscribe</a></div>
                    </div>

                    <div class="col-md-5">
                        <div>subscription</div>
                        %{--<div>${topic.subsCount}</div>--}%
                        <div>
                            <ls:topicSubscriptionCount topicId="${topic.id}"/>
                        </div>
                    </div>

                    <div class="col-md-2">
                        <div>post</div>
                        %{--<div>${topic.count}</div>--}%
                        <div>
                            <ls:resourceCount topicId="${topic.id}"/>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            %{--<hr style="border: 1px solid ">--}%
            <!-- <div class="row"> -->
        </div>

        <div class="row pull-right">
            <ls:showSeriousness topicId="${topic.id}"/>
            <g:if test="${session.user.userName == topic.createdBy.userName}">
                <span class="dropdown">
                    <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">private <b
                            class="caret"></b></button>
                    <ul class="dropdown-menu" role="menu">
                        <li role="presentation"><a href="#" role="menuitem">public</a></li>
                        <li role="presentation"><a href="#" role="menuitem">private</a></li>
                    </ul>
                </span>
            </g:if>
            <span style="display: inline-block;">
                <span data-toggle="modal" data-target="#sendInvitation">
                    <i class="fa fa-envelope fa-2x"></i>
                </span>
                <g:render template="/topic/invite"/>
                <g:if test="${session.user.userName == topic.createdBy.userName}">
                    <a href="#"><span class="glyphicon glyphicon-edit glyphsize"></span></a>
                    <a href="#"><span class="glyphicon glyphicon-trash glyphsize"></span></a>
                </g:if>
            </span>
        </div><br>
    </div>
</g:each>
<script type="text/javascript">
    function updateSeriousness(val) {
        jQuery.ajax({
            type: 'POST',
            data: {'subscriptionId': val.id, 'seriousness': val.value},
            url: '/subscription/update',
        });
    }
</script>