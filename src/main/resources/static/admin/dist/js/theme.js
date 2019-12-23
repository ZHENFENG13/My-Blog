function useTheme (themeName){
    $.ajax({
        type: "POST",
        url: "/admin/theme/set",
        data: {themeName},
        success: function (result) {
            debugger;
            if (result.resultCode == 200 && result.data) {
                swal("设置成功", {
                    icon: "success",
                });
                reload();
            }
            else {
                swal(result.message, {
                    icon: "error",
                });
            }
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
}

function reload() {
    window.location.href= "/admin/theme";
}