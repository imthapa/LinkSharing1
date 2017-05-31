<g:each in="${resourceList}" var="resource">
    <div class="well">
        <div class="col-sm-2">
            %{--<div class="well">--}%
            <pic:userImage id="${resource.createdBy.id}"/>
            %{--</div>--}%
            <!--<span class="glyphicon glyphicon-user "></span>-->
            <!-- %{--<img src="../images/user_image.jpg"/>--}% -->
        </div>

        <div class="col-sm-10 ">
            <div class=" user_details">
                <label>${resource.createdBy.fullName}</label>
                &nbsp;&nbsp;&nbsp;
                <label><g:link action="profile" controller="user" params='["id": "${resource.createdBy.id}"]'>@${resource.createdBy.userName}</g:link></label>
                <g:link action="show" class="pull-right" controller="topic"
                        params='["id": "${resource.topicId}"]'>${resource.topicName}</g:link>
            </div>

            <div class="">
                ${resource.resourceDescription}
            </div>
        </div>

        <div class="">
            <a href="https://www.facebook.com" style="margin-right:10px">
                <i id="social-fb" class="fa fa-facebook-square fa-2x social"></i></a>
            <a href="https://twitter.com" style="margin-right:10px">
                <i id="social-tw" class="fa fa-twitter-square fa-2x social"></i></a>
            <a href="https://plus.google.com" style="margin-right:10px">
                <i id="social-gp" class="fa fa-google-plus-square fa-2x social"></i></a>
            <a href="mailto:bootsnipp@gmail.com" style="margin-right:10px">
                <i id="social-em" class="fa fa-envelope-square fa-2x social"></i></a>
            <span class="pull-right">
                <g:if test="${session.user}">

                    <g:if test="${resource.isLinkResource()}"  >
                        <g:link controller="resource" action="newLink" style="margin-right:10px" params='["id": "${resource.resourceID}"]'
                                target="_blank">view full site</g:link>
                    </g:if>
                    <g:else>
                        <g:link action="download" controller="resource" style="margin-right:10px"
                                params='["id": "${resource.resourceID}"]'><label>Download</label></g:link>
                    </g:else>

                  %{--  <a href="#" class="operations" >Download</a>
                    <a href="#" class="operations" style="margin-right:10px">View Full Site</a>--}%
                    <span><ls:markAsRead resource="${resource}"/></span>
                </g:if>
                <g:link action="viewPost" controller="resource"
                        params='["id": "${resource.resourceID}"]'><label>View post</label></g:link>
            </span>
        </div>
    </div>
</g:each>
<g:paginate total="${resourceList.size()}" max="5"/>
