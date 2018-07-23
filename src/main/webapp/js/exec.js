
var e = ace.edit("jsEditor");
	e.getSession().setMode("ace/mode/java");
	e.setTheme("ace/theme/eclipse");
	e.getSession().setTabSize(2);
	e.getValue();
	
	var display = function() {
		var e = ace.edit("jsEditor");
		e.getValue();
	}

	
	 e.setOptions({
         enableBasicAutocompletion: true,
         enableSnippets: true,
         enableLiveAutocompletion: false
     })
	
	
	
    function getvalue() {
        return e.getValue();
    }

    function copyselection() {
        return e.getCopyText();
    }

    function pastevalue(aValue) {
        e.insert(aValue);
    }