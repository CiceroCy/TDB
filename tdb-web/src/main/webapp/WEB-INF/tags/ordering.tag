<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ attribute name="pageResult" required="true"
    type="org.springframework.data.domain.Page"%>
<%@ attribute name="actionLink" required="true" type="java.lang.String"%>
<%@ attribute name="actionFormId" required="true"
    type="java.lang.String"%>
<%@ attribute name="columnName" required="true" type="java.lang.String"%>


<script type="text/javascript">
    function submitPageOrder(actionLinkURL, actionFormId, actionLinkDefault) {
        var form = $("form#" + actionFormId);
        var actionFormOld = actionLinkDefault;
        var actionFormNew = actionLinkURL;
        var params = actionFormNew.replace(actionFormOld + '?', '');
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
                                elementId).attr('id', elementId).attr('value',
                                elementValue).appendTo(form);
                    });

            $(form).attr("action", actionFormOld);
        } else {
            $(form).attr("action", actionFormNew);
        }
        
       
        $(form).submit();
    }

    var OrderingTag = {
        goOrdering : function(actionLinkURL, actionFormId, actionLinkDefault,
                columnName) {
            submitPageOrder(actionLinkURL, actionFormId, actionLinkDefault);
        }
    };
</script>

<c:set var="defaultUrl"
    value="${actionLink}?page.page=1&page.size=${pageResult.size}" />
<c:url var="upOrder"
    value="${actionLink}?page.page=1&page.size=${pageResult.size}&page.sort=${columnName }&page.sort.dir=DESC" />

<c:url var="downOrder"
    value="${actionLink}?page.page=1&page.size=${pageResult.size}&page.sort=${columnName }&page.sort.dir=ASC" />


<c:choose>
    <c:when test="${!empty pageResult.sort}">
        <c:choose>
            <c:when test="${pageResult.sort.getOrderFor(columnName).direction == 'ASC'}">
                <b class=" icon-chevron-down margin-table order " onclick="javascript: OrderingTag.goOrdering('${upOrder}', '${actionFormId}', '${actionLink}');"  id="icon-ordering-${columnName}-down"></b>
            </c:when>
            <c:when test="${pageResult.sort.getOrderFor(columnName).direction == 'DESC'}">
                <b class="icon-chevron-up margin-table order" onclick="javascript: OrderingTag.goOrdering('${downOrder}', '${actionFormId}', '${actionLink}');"  id="icon-ordering-${columnName}-up"></b>
            </c:when>
            <c:otherwise>
               <b class=" icon-chevron-down margin-table order" onclick="javascript: OrderingTag.goOrdering('${upOrder}', '${actionFormId}', '${actionLink}');"  id="icon-ordering-${columnName}-down"></b>
               <b class="icon-chevron-up margin-table order" onclick="javascript: OrderingTag.goOrdering('${downOrder}', '${actionFormId}', '${actionLink}');"  id="icon-ordering-${columnName}-up"></b>
            </c:otherwise>
        </c:choose>     
    </c:when>
    <c:otherwise>
        <b class="icon-chevron-down margin-table order" onclick="javascript: OrderingTag.goOrdering('${upOrder}', '${actionFormId}', '${actionLink}');"  id="icon-ordering-${columnName}-down"></b>
        <b class="icon-chevron-up margin-table order" onclick="javascript: OrderingTag.goOrdering('${downOrder}', '${actionFormId}', '${actionLink}');"  id="icon-ordering-${columnName}-up"></b>
    </c:otherwise>
</c:choose>