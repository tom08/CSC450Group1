<%@include file="stdLayout.jsp" %>
<%@ page session="false" %>
<style>
div.result:hover{
    cursor:pointer;
}
</style>
<div class="container">
    <div class="row">
        <h2 class="text-center">Estimate an Ad Value</h2>
        <p class="text-center">Pick which keywords will be on the page with the ad to get an estimated value for that ad.</p>
        <form class="form" method="POST" action="submitKeywords">
            <div class="col-md-6">
                <label>Search Keywords
                    <input id="keyword-search" class="form-control" type="text" placeholder="Start typing a keyword!"/>
                </label>
                <div id="keyword-results"></div>
            </div>
            <div class="col-md-6">
                <input class="btn btn-primary" type="submit" value="Submit Keywords">
                <hr/>
                <select id="selected-keywords" class="hidden" multiple name="keywords">
                </select>
                <div id="display-selected"></div>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function (){
        function get_keywords(search_box){
            if(search_box.val().length > 1){
                if(search_box.val()[0] == "/")
                     return;
                $.ajax({
                    url:"get_similar_keywords/"+search_box.val(),
                    error:function(error){
                        console.log("error");
                    },
                    success:function(response){
                        $('div#keyword-results').empty();
                        $('div#keyword-results').append(response);
                        $('div.result').click(function(){
                            var select = $('select#selected-keywords');
                            var display = $('div#display-selected');
                            var already_selected = false;
                            var id = $(this).data('id');
                            $('div.selected-keywords button').each(function (){
                                if($(this).data('id') == id)
                                    already_selected = true;
                            });
                            if(!already_selected){
                                select.append('<option value="'+id+'" selected>'+$(this).data('kname')+'</option>');
                                var html = '<div class="alert alert-success selected-keywords">';
                                html += $(this).data('kname');
                                html += '<button id="remove-'+id+'" class="btn btn-xs btn-danger pull-right remove"';
                                html += ' data-id="'+id+'">Remove</button>';
                                html += '</div>';
                                display.append(html);
                                $(this).remove();
                                $('button#remove-'+id).click(function (){
                                    $('select option[value="'+$(this).data('id')+'"]').remove();
                                    $(this).closest('div.selected-keywords').remove();
                                });
                            }
                        });
                    }
                });
            }
            else if(!search_box.val()){
                $('div#keyword-results').empty();
            }
        }

        $('input#keyword-search').keyup(function (){
            console.log("hey")
            get_keywords($(this));
        });
    });
</script>
</body>
</html>
