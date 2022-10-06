var hook = true;
window.onbeforeunload = function() {
    if (hook) {
        return "Did you save your stuff?"
    }
}
function unhook() {
    hook=false;
}

document.addEventListener('click', event => {
    if (event.target.tagName.toLowerCase() === 'a') {
        hook = false;
    }
});
document.addEventListener('submit', event => {
    hook = false;
});



function save() {

    var d = document,
        stats = d.getElementsByClassName('choise')
    str = "";
    for (var i = 0; i < stats.length; i++) {

        str += stats[i].value;
        str+='/';
    }
    document.forms["sethud"].elements["allstatsname"].value = str;
    hook = false;
    document.getElementById('sethud').submit();
}
function rollback() {
    document.getElementById('rollback').href="/hud-edit/"+document.forms["createhud"].elements["roundOfBidding"].value;
    document.getElementById('rollback').click();
}


