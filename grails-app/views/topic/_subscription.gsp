<%@ page import="com.ttnd.linksharing.util.Seriousness" %>

<div class="col-lg-5">
    <g:select id="seriousness" name="${subscription.id}" value="${subscription.seriousness}"
              from="${Seriousness.values()}"
              onchange="changeSeriousness(this)" />
</div>

<div class="col-lg-2">
    <a href="" data-toggle="modal" data-target="#sendInvitation">
        <span class="fa fa-envelope fa-2x"></span>
    </a>
</div>

<script type="text/javascript">
    function changeSeriousness(element) {
//        alert(element.name)
        console.log(element.name)
        console.log(element.value)
        jQuery.ajax({
            type:'POST',
            data:{'id': element.name, 'seriousness' : element.value},
            url:'/subscription/update',
        });
    }
</script>


