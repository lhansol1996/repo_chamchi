// 관리자 화면 체크박스 js 
document.querySelector('#checkboxAll');

    checkAll.addEventListener('click', function(){

        const isChecked = checkAll.checked;

        if(isChecked){
            const checkboxes = document.querySelectorAll('.chk');

            for(const checkbox of checkboxes){
                checkbox.checked = true;
            }
        }

        else{
            const checkboxes = document.querySelectorAll('.chk');
            for(const checkbox of checkboxes){
                checkbox.checked = false;
            }
        }
    })
    
    const checkboxes = document.querySelectorAll('.chk');
    for(const checkbox of checkboxes){
        checkbox.addEventListener('click',function(){
        
        const totalCnt = checkboxes.length;
        
        const checkedCnt = document.querySelectorAll('.chk:checked').length;
        
        if(totalCnt == checkedCnt){
            document.querySelector('#checkboxAll').checked = true;
        }
        else{
            document.querySelector('#checkboxAll').checked = false;
        }
        
        });
        
    }