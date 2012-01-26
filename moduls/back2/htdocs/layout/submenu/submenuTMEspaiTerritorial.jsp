<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
	function montarBreadcrumb() {
		item_ID = $("#item_id").val();
		dataVars = "id=" + item_ID;
		$.ajax({
			type: "POST",
			url: pagBreadcrumb,
			data: dataVars,
			dataType: "json",
			success: function(data) {
				if ($(".breadItems").length) {
					$(".breadItems").remove();
					$("#submenu").removeClass("breadcrumb");
				}
				$("#submenu .actual").before("<span class='breadItems'>");
				$(".breadItems").append($("<a>").text("<spring:message code='txt.arrel'/>").click(function() {
					Detall.torna();
					if ($(".breadItems").length) {
						$(".breadItems").remove();
						$("#submenu").removeClass("breadcrumb");
					}
				}));
				
				for (var i in data.breadcrumb) {
					var espai = data.breadcrumb[i];
					$(".breadItems").append("»");
					$(".breadItems").append(
						$(document.createElement('a')).text(espai.nom).attr('id', espai.id).click(function() {
							var espaiId = $(this).attr('id');
							Detall.recarregar(espaiId);
						})
					);
				}
				$("#submenu").addClass("breadcrumb");
			}
		});
	}
</script>
<div id="submenu">
	<div id="submenu_contingut">
		<ul>
	        <li class="seleccionat">
	            <span><spring:message code="submenu.espais_territorials" /></span>
	            <span class="actual"></span>
	        </li>
        </ul>			
	</div>		
</div>