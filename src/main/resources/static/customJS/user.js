$(document).ready(function() {
	fetch_data();
	function fetch_data(){	
		$.ajax({
			url : '/user',
			method : 'get',
			dataType : 'json',
			beforeSend: function(){
				$('.loader').show();
			},
			complete: function(){
				$('.loader').hide();
			},
			success : function(data) {
				$('#datatable').dataTable({
					paging : true,
					sort : true,
					searching : true,
					destroy: true,
					data : data,
					columns : [ {
						'data' : 'firstName'
					}, {
						'data' : 'lastName'
					}, {
						'data' : 'fathersName'
					}, {
						'data' : 'phone'
					}, {
						'data' : 'password'
					}, {
						'data' : 'currentAddress'
					}, {
						'data' : 'permanentAddress'
					}, {
						'data' : 'status'
					}, {
						'data' : 'securityQuestion'
					}, {
						'data' : 'securityAns'
					} , {
						'data': null,
						'sortable': false,
						'searchable': false,
						 className: "center",
						'render': function (data) {
						return '<a id="' + data.userId + '" href="#" title="Edit this record" class="btn btn-primary edit" data-toggle="modal" data-target="#editmodel"><i class="fa fa-edit"></i></a>  <a id="' + data.userId + '" href="#" class="btn btn-danger delete" title="Delete this record" ><i class="fa fa-trash"></i></a>';
						}
					}
					]
				}); 
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alertify.error(jqXHR.responseJSON.error);
			}
		});
	}
	$('#datatable').on('click','tr  a[title="Edit this record"]',function(){
		resetForm();
		var table = $('#datatable').DataTable();
		var row = table.row($(this).closest('tr')).data();
			
		$('[name="userId"]').val(row.userId);
		$('[name="firstName"]').val(row.firstName);
		$('[name="lastName"]').val(row.lastName);
		$('[name="fathersName"]').val(row.fathersName);
		$('[name="phone"]').val(row.phone);
		$('[name="password"]').val(row.password);
		$('[name="currentAddress"]').val(row.currentAddress);
		$('[name="permanentAddress"]').val(row.permanentAddress);
		$('select').val(row.securityQuestion);
		$('[name="securityAns"]').val(row.securityAns);
	});
		
	$('#datatable').on('click','tr a[title="Delete this record"]',function(){
		var userId = $(this).attr('id');
	
		alertify.confirm("Are you sure you want to delete this user?", function (e) {
			if (e) {
				$.ajax({  
					url:"/user/"+userId,  
					method:"DELETE", 
					dataSrc: "",  
					success:function(data){  
						fetch_data();
					},
					error: function(status)
					{
						console.log(status);
						alertify.error(status)
					}
				}); 
			} else {
				alertify.success('cancel operation');
			}
		}).set({ buttonFocus: "cancel" }); 
	
	});
	
	/* Updated new Item */
	$(".updatebtn").click(function(e){
		$('.msg').css({'display': 'none'});
		var form_action = $("#edit-item").find("form").attr("action");
		
		var userId = $("#edit-item").find("input[name='userId']").val();
		var firstName = $("#edit-item").find("input[name='firstName']").val();
		var lastName = $("#edit-item").find("input[name='lastName']").val();
		var fathersName = $("#edit-item").find("input[name='fathersName']").val();
		var phone = $("#edit-item").find("input[name='phone']").val();
		var password = $("#edit-item").find("input[name='password']").val();
		var currentAddress = $("#edit-item").find("input[name='currentAddress']").val();
		var permanentAddress = $("#edit-item").find("input[name='permanentAddress']").val();
		var status = $("#edit-item").find("#status").val();
		var securityQuestion = $("#edit-item").find("#securityQuestion").val();
		var securityAns = $("#edit-item").find("input[name='securityAns']").val();
		
		var dataObj = {
			userId:userId, 
			firstName:firstName,
			lastName:lastName,
			fathersName:fathersName,
			phone:phone,
			password:password,
			currentAddress:currentAddress,
			permanentAddress:permanentAddress,
			securityQuestion:securityQuestion,
			status:status,
			securityAns:securityAns
		};
		
		var stringfyData = JSON.stringify(dataObj);
		if(userId && formValidation()){
			$.ajax({  
				url:form_action,  
				method:"POST", 
				dataSrc: "", 
				headers : {
					'Content-Type' : 'application/json'
				},
				data: stringfyData,
				success:function(data){
					alertify.success('Update successfully');
					$('#editmodel').modal('hide');
					fetch_data();
				},
				error: function(jqXHR, textStatus, errorThrown)
				{
					console.log(jqXHR);
					alertify.error(jqXHR.responseJSON.error)
				}
			}); 
		}
	});
	
	//validation
	$('#confirm_password').on('blur', function () {
		var msg = $("#message");
		if ($('#password').val() !== $('#confirm_password').val()) {
			msg.html('Not Matching').css({'display': 'block', 'color': 'red', 'opacity' : 100});
			msg.fadeTo(5000, 0).slideUp(500, function() {
				msg.slideUp();
			});
		}
	});
	
	function resetForm(){
		$('#userForm').closest('form').find("input[type=text], input[type=password], select").val("");
		$('.msg').css({'display': 'none'});
	}
	
	//show password strength
	$('#password').keyup(function() {
		$('#pwd_strength').html(checkStrength($('#password').val())).css({'display' : 'block', 'opacity' : 100});
	});
	
	//hide strength message
	$('#password').on('blur', function () {
		$('#pwd_strength').fadeTo(5000, 0).slideUp(500, function() {
			$('#pwd_strength').hide();
		});
	});
	
	function checkStrength(password) {
		var meter = $('#pwd_strength');
		var strength = 0
		if (password.length < 6) {
			meter.removeClass()
			meter.addClass('short')
			return 'Too short'
		}
		if (password.length > 7) strength += 1
			// If password contains both lower and uppercase characters, increase strength value.
		if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) { strength += 1 }
			// If it has numbers and characters, increase strength value.
		if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) { strength += 1 }
			// If it has one special character, increase strength value.
		if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) { strength += 1 }
			// If it has two special characters, increase strength value.
		if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) { strength += 1 }
			// Calculated strength value, we can return messages
		if (strength < 2) {
			meter.removeClass()
			meter.addClass('weak')
			return 'Weak'
		} else if (strength == 2) {
			meter.removeClass()
			meter.addClass('good')
			return 'Good'
		} else {
			meter.removeClass()
			meter.addClass('strong')
			return 'Strong'
		}
	}
	
	function formValidation(){
		var firstName = $("#edit-item").find("input[name='firstName']").val();
		var fathersName = $("#edit-item").find("input[name='fathersName']").val();
		var phone = $("#edit-item").find("input[name='phone']").val();
		var password = $("#edit-item").find("input[name='password']").val();
		var currentAddress = $("#edit-item").find("input[name='currentAddress']").val();
		var securityQuestion = $("#edit-item").find("#securityQuestion").val();
		var securityAns = $("#edit-item").find("input[name='securityAns']").val();
		
		if(!firstName || firstName.length < 5 || firstName.trim().length == 0){
			$('.firstNamemsg').css({'display': 'block', 'color': 'red'});
			$('#firstName').focus();
			return false;
		}
		else if(!fathersName || fathersName.length < 5 || fathersName.trim().length == 0){
			$('.fatherNamemsg').css({'display': 'block', 'color': 'red'});
			$('#fathersName').focus();
			return false;
		}
		else if(!phone || phone.length < 11 || phone.trim().length == 0){
			$('.phonemsg').css({'display': 'block', 'color': 'red'});
			$('#phone').focus();
			return false;
		}
		else if(!password || password.length < 8 || phone.trim().length == 0){
			$('#pwd_strength').html('Password should be minimum 8 charecter').css({'display': 'block', 'color': 'red', 'opacity' : 100});
			$('#password').focus();
			return false;
		}
		else if(password != $('#confirm_password').val()){
			$("#message").html('Password Doesnt match').css({'display': 'block', 'color': 'red', 'opacity' : 100});
			$('#confirm_password').focus();
			return false;
		}
		else if(!currentAddress || currentAddress.trim().length == 0){
			$('.currentAddressmsg').css({'display': 'block', 'color': 'red'});
			$('#currentAddress').focus();
			return false;
		}
		else if(!securityQuestion){
			$('.securityQuestionmsg').css({'display': 'block', 'color': 'red'});
			$('#securityQuestion').focus();
			return false;
		}
		else if(!securityAns || securityAns.trim().length == 0){
			$('.securityAnsnmsg').css({'display': 'block', 'color': 'red'});
			$('#securityAns').focus();
			return false;
		}
		else {
			return true;
		}
	}
});