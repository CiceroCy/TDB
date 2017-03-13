<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ attribute name="pageResult" required="false"
    type="org.springframework.data.domain.Page"%>
<%@ attribute name="actionLink" required="false" type="java.lang.String"%>
<%@ attribute name="actionFormId" required="false"
    type="java.lang.String"%>

<c:set var="defaultUrl"
    value="page.size=${pageResult.size}" />
<c:set var="currentIndex" value="${pageResult.number + 1}" />

<c:url var="firstUrl" value="${actionLink}?page.page=1&${defaultUrl}" />
<c:url var="lastUrl"
    value="${actionLink}?page.page=${pageResult.totalPages}&${defaultUrl}" />
<c:url var="prevUrl"
    value="${actionLink}?page.page=${currentIndex - 1}&${defaultUrl}" />
<c:url var="nextUrl"
    value="${actionLink}?page.page=${currentIndex + 1}&${defaultUrl}" />

<c:choose>
    <c:when test="${!empty pageResult.sort}">
        <c:set var="sortJavaObject" value="${pageResult.sort}"/>
    </c:when>
    <c:otherwise>
        <c:set var="sortJavaObject" value="" />
    </c:otherwise>
</c:choose>



<script type="text/javascript">
    var PagingTag = {
        send : function(actionLinkURL, actionFormId, actionLinkDefault) {
            var form = $("form#" + actionFormId);
            var actionFormOld = actionLinkDefault;
            var actionFormNew = actionLinkURL;
            var params = actionFormNew.replace(actionFormOld + '?', '');

            <c:if test="${!empty pageResult.sort}">
                var orderByObject = "${sortJavaObject}";
                var orderArr = orderByObject.split(":");
                var orderByPagingTag = "&page.sort="+$.trim(orderArr[0])+"&page.sort.dir="+$.trim(orderArr[1]); 
                params = params + orderByPagingTag;
            </c:if>

            
            var paramArr = params.split("&");

            if (($(form).attr('method')).toUpperCase() == 'POST') {
                //transforma os parametros de get para post
                $(paramArr).each(
                        function(i, element) {
                            elementArr = element.split('=');

                            var elementId = elementArr[0];
                            var elementValue = elementArr[1];

                            //adiciona um item do tipo hidden ao form
                            $('<input />').attr('type', 'hidden').attr('name',
                                    elementId).attr('id', elementId).attr(
                                    'value', elementValue).appendTo(form);
                        });

                $(form).attr("action", actionFormOld);
            } else {
                $(form).attr("action", actionFormNew);
            }
            $(form).submit();
        }
    }
</script>

<div class="pagination pagination-centered">
   <ul class="the-icons clearfix">
    

    <c:choose>
        <c:when test="${pageResult.hasPreviousPage()}">
            <li><a href="javascript:void(0);" onclick="javascript: PagingTag.send('${firstUrl}', '${actionFormId}', '${actionLink}') "><i class="icon-step-backward icon-white icon-small"></i>&nbsp;</a></li>
            <li><a href="javascript:void(0);" onclick="javascript: PagingTag.send('${prevUrl}', '${actionFormId}', '${actionLink}') "><i class="icon-chevron-left icon-white icon-small"></i>&nbsp;</a></li>
        </c:when>
        <c:otherwise>
            <li class="disabled"><a href="javascript:void(0);" ><i class="icon-step-backward icon-white icon-small"></i>&nbsp;</a></li>
            <li class="disabled"><a href="javascript:void(0);" ><i class="icon-chevron-left icon-white icon-small"></i>&nbsp;</a></li>
        </c:otherwise>
    </c:choose>

    <li class="disabled"><a href="javascript:void(0);">${currentIndex} de ${pageResult.totalPages}</a></li>
    
    <c:choose>
        <c:when test="${pageResult.hasNextPage()}">
            <li><a href="javascript:void(0);"><i class="icon-chevron-right icon-white icon-small" onclick="javascript: PagingTag.send('${nextUrl}', '${actionFormId}', '${actionLink}') "></i>&nbsp;</a></li>
            <li><a href="javascript:void(0);"><i class="icon-step-forward icon-white icon-small" onclick="javascript: PagingTag.send('${lastUrl}', '${actionFormId}', '${actionLink}') "></i>&nbsp;</a></li>
        </c:when>
        <c:otherwise>
            <li class="disabled"><a href="javascript:void(0);"><i class="icon-chevron-right icon-white icon-small"></i>&nbsp;</a></li>
            <li class="disabled"><a href="javascript:void(0);"><i class="icon-step-forward icon-white icon-small"></i>&nbsp;</a></li>
        </c:otherwise>
    </c:choose>


  </ul>
</div>
     
