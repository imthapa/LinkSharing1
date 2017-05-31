<%@ page import="com.ttnd.linksharing.util.Visibility" %>

<div class="col-lg-4">
    <g:select id="visibility" class="form-control" name="${topic.id}" value="${topic.visibility}"
              from="${Visibility.values()}"
              onchange="changeVisibility(this)" />
</div>



<script type="text/javascript">
    function changeVisibility(element) {
        jQuery.ajax({
            type:'POST',
            data:{'id': element.name, 'visibility' : element.value},
            url:'/topic/updateVisibility',
            success:function () {
                location.load()
            }
        });
    }
</script>


