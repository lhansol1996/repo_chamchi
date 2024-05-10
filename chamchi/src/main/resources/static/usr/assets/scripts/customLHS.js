let formTop = document.querySelector("form[name=formTop]");
let goUrlCharacterDetail = "/characterDetail";
let goUrlRecord = "/record";
let searchInput = document.getElementById("XtopBarSearchInputValue").value;
let searchInput2 = document.getElementById("XtopBarSearchInputValue2").value;

document.getElementById("searchTopBtn").onclick = function () {
    goFormTop(searchInput);
}

goFormTop = function () {
    formTop.action = goUrlCharacterDetail;
    formTop.submit();
}

function enterkeyTop1(event) {
    if (event.key === 'Enter') {
        goFormTop(searchInput);
    }
}

document.getElementById("searchTopBtn2").onclick = function () {
    goFormTop2(searchInput2);
}

goFormTop2 = function () {
    formTop.action = goUrlRecord;
    formTop.submit();
}

function enterkeyTop2(event) {
    if (event.key === 'Enter') {
        goFormTop2(searchInput2);
    }
}

// 로그아웃 버튼 - usr
document.getElementById("btnLogout").onclick = function () {
        $.ajax({
            async: true
            , cache: false
            , type: "post"
            /* ,dataType:"json" */
            , url: "/memberLogout"
            /* ,data : $("#formLogin").serialize() */
            // , data: { "memberID": $("#memberID").val(), "memberPwd": $("#memberPwd").val() }
            , success: function (response) {
                alert(response.rt);
                if (response.rt == "success") {
                    alert('로그아웃 성공');
                        location.href = "/userIndex";
                } else {
                    alert('로그아웃 안됨');
                    // document.getElementById("modalAlertTitle").innerText = "확 인";
                    // document.getElementById("modalAlertBody").innerText = "일치하는 회원이 존재 하지 않습니다!";
                    // $("#modalAlert").modal("show");
                }
            }
            , error: function (jqXHR, textStatus, errorThrown) {
                alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
            }
        });
        

    }