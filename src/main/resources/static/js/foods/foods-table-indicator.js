let $sortable = $('.sortable');

$sortable.on('click', function(){

    let $this = $(this);
    let asc = $this.hasClass('asc');
    let desc = $this.hasClass('desc');
    $sortable.removeClass('asc').removeClass('desc');
    if (!asc && !desc) {
        $this.addClass('asc');
    } else if(asc){
        $this.addClass('desc');
    }
});