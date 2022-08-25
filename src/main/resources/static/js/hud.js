
    const toastTrigger = document.getElementById('liveToastBtn3')
    const toastLiveExample = document.getElementById('liveToast')
    const toastTrigger2 = document.getElementById('liveToastBtn2')
    const toastLiveExample2 = document.getElementById('liveToast2')
    if (toastTrigger) {
    toastTrigger.addEventListener('click', () => {
        const toast = new bootstrap.Toast(toastLiveExample)

        toast.show()
    })

}
    if (toastTrigger2) {
    toastTrigger2.addEventListener('click', () => {
        const toast = new bootstrap.Toast(toastLiveExample2)

        toast.show()
    })

}
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

    function ready() {
    alert('DOM готов');
}

    document.addEventListener("DOMContentLoaded",function(){
    const m = document.getElementById('wow');
    if (m === null) {

} else {

    const toast = new bootstrap.Toast(toastLiveExample2)
    toast.show();
}

    switch(sessionStorage.getItem('tab')) {
    case 'preflop':
    document.getElementById("preflop-list").click();
    break;

    case 'flop':
    document.getElementById("flop-list").click();
    break
    case 'turn':
    document.getElementById("turn-list").click();
    break
    case 'river':
    document.getElementById("river-list").click();
    break
    default:
    document.getElementById("preflop-list").click();
    break
}


})
    window.addEventListener("unload",function(){
    if (document.getElementById("preflop-list").tabIndex!=-1)
{
    sessionStorage.setItem('tab', "preflop")
}
    else if (document.getElementById("flop-list").tabIndex!=-1)
{
    sessionStorage.setItem('tab', "flop")
}
    else if (document.getElementById("turn-list").tabIndex!=-1)
{
    sessionStorage.setItem('tab', "turn")
}
    else if(document.getElementById("river-list").tabIndex!=-1)
{
    sessionStorage.setItem('tab', "river")
}
    else
{
    sessionStorage.setItem('tab', "something went wrong")
}
})
    function getStatId2(statID){
    document.getElementById(statID).submit();
    return false;
}
    function getStatId(stat, player) {

        document.forms["setStat"].elements["stat"].value = stat;
        document.forms["setStat"].elements["statplayer"].value = player;
        document.getElementById('setStat').submit();
        return false;
    }
