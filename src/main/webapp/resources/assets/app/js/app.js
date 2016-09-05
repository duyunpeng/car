/**
 * Created by YJH on 2016/3/10.
 */
$(function() {
    $(":input").click(function(){
        var node = $(this).next();
        if(node.hasClass("parsley-errors-list")){
            node.remove();
        }
    });
});