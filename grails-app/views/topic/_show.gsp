<%@ page import="com.ttnd.linksharing.util.Seriousness" %>
<!-- <div class="row"> -->
<g:each in="${topics}" var="topic">
    <g:render template="/topic/inviteForTopic" model="[topic: topic]"/>
    <g:render template="/topic/editTopic" model="[topic: topic]"/>
    <div class="well">
        <div class="row locateHider">
            <div class="col-sm-2">
                <pic:userImage id="${topic.createdBy.id}"/>
            </div>

            <div class="col-sm-10 ">
                <div>
                    %{--<g:textField name="topicname" class="form-control" value="${topic.name}" >--}%
                    <g:link action="show" controller="topic" params='["id": "${topic.id}"]'>
                        ${topic.name}
                    </g:link>
                    <span class="text-muted">
                        (${topic.visibility})
                    </span>
                </div>

                <div>
                    <div style="padding-left: 0px" class="col-md-5">
                    <div><g:link action="profile" controller="user" params='["id": "${topic.createdBy.id}"]'>${topic.createdBy.userName}</g:link></div>
                        <div><a href="" name="${topic.id}" onclick="changeSubscription(this)"><ls:subscriptionToggle
                                id="${topic.id}"/></a></div>
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
                        <div>
                            <ls:resourceCount topicId="${topic.id}"/>
                        </div>
                    </div>
                </div>
            </div>
            <br>
        </div>

        <div class="row">
            <span >
                <ls:showSeriousness topicId="${topic.id}"/>
                <g:if test="${session.user.userName == topic.createdBy.userName}">
                    <ls:showVisibility topicId="${topic.id}"/>
                </g:if></span>
            <span class="pull-right">
                <span class="glyphicon glyphicon-envelope glyphsize" data-toggle="modal"
                      data-target="#invitefortopic"></span>

                <g:if test="${session.user.userName == topic.createdBy.userName}">
                    <a id="edit" ><span class="glyphicon glyphicon-edit glyphsize" data-toggle="modal"
                      data-target="#editTopic"></span>
                    <g:link class="glyphicon glyphicon-trash glyphsize" controller="topic" action="delete"
                            params='["id": "${topic.id}"]'>

                    </g:link>
                </g:if>
            </span>
        </div>
    </div>
</g:each>

<script type="text/javascript">

    function changeSubscription(element) {
        console.log(element.name)
        jQuery.ajax({
            type: 'POST',
            data: {'id': element.name},
            url: '/subscription/save',
            success: function () {
                location.reload()
            }
        });
    }
   /* $('#edit').on('click', function (event) {
        event.stopPropagation()
        event.stopImmediatePropagation()
        $(this).closest('.locateHider').find()
    })
    $("#cancel").click(function () {
        $("#changer").hide();
    });*/
    /* function updateSeriousness(val) {
     jQuery.ajax({
     type: 'POST',
     data: {'subscriptionId': val.id, 'seriousness': val.value},
     url: '/subscription/update',
     });
     }*/
</script>