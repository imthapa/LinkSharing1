<%@ page import="linksharing.Resource; linksharing.LinkResource" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Dashboard</title>
    <meta name="layout" content="main"/>
</head>

<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading clearfix">
            %{--<div class="row"  style="padding:0px;margin: 0px">--}%
            <div class="col-md-6 ">
                <h4>Posts</h4>
            </div>

            <div class="col-md-6">
                <div class="col-md-6">
                    <g:form class="search-form" controller="topic" action="search">
                        <div class="form-group has-feedback">
                            %{--<label class="sr-only">Search</label>--}%
                            <g:hiddenField name="id" value=""/>
                            <g:hiddenField name="max" value="10"/>
                            <g:hiddenField name="offset" value="0"/>
                            <input style="border-radius: 20px" type="text" class="form-control" name="q" id="q"
                                   placeholder="search">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </g:form>
                </div>
            </div>
            %{--</div>--}%
        </div>

        %{--<div class="panel-body">--}%
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                %{--<th>Resource Name</th>--}%
                <th>Created By</th>
                <th>Topic Under</th>
                <th>Description</th>
                <th>Resource</th>
                %{--<th>Link</th>--}%
                %{--<th>Update</th>--}%
            </tr>
            </thead>
            <tbody>
            <g:each in="${posts}" var="post">
                <tr>
                    <td>${post.id}</td>
                    <td><g:link action="profile" controller="user" params='["id": "${post.createdBy.id}"]'>${post.createdBy.userName}</g:link></td>
                    <td><g:link action="show" controller="topic" params='["id": "${post.topic.id}"]'>${post.topic.name}</g:link></td>
                    <td>${post.description}</td>
                    <g:if test="${post instanceof LinkResource}">
                        <td>${post.url}</td>
                    </g:if>
                    <g:else>
                        <td>${post.filePath}</td>
                    </g:else>
                </tr></g:each>
            </tbody>
        </table>
        %{--</div>--}%
        <a onclick="paginate(this)"><g:paginate total="${Resource.count()}" max="10"/></a>
    </div>
</div>
<script type="text/javascript">
    function paginate(element) {
//        alert(element.name)
        $.ajax({
            type: 'POST',
//            data: {id: element.name},
            url: '/admin/index',
            success: function () {
//                location.reload()
            }

        })
    }
</script>
</body>
</html>
