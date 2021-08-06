$(document).ready(function(){
    var code = $('.calculationFunction')[0];
    var editor = CodeMirror.fromTextArea(code, {
        lineNumbers : true,
        mode: "groovy",
        theme: "idea",
        closeBrackets: true,
        matchBrackets: true
    });
    editor.border = 1;
});