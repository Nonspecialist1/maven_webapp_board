function moveToDetail(iboard){
    location.href='/board/detail?iboard=' + iboard;
}
var searchElem = document.querySelector('#searchFrm');
if(searchElem){
    searchElem.rowCnt.addEventListener('change', function(e){
        searchElem.submit();
    })
}