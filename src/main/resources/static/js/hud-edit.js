function save() {

    var d = document,
        stats = d.getElementsByClassName('choise')
    str = "";
    for (var i = 0; i < stats.length; i++) {

        str += stats[i].value;
        str+='/';
    }
    document.forms["sethud"].elements["allstatsname"].value = str;
    alert(str);
}
