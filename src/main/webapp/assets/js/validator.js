class Validator {
    constructor(options) {
        this.options = options
        this.formElement = document.querySelector(this.options.form)
        this.selectorRules = {}               
    }    

    execute(){
        
        const removeErrorMessage = (errorElement, inputElement) => {
            errorElement.innerText = ''
            getParentElement(inputElement, this.options.formGroupSelector).classList.remove('invalid')
        }

        const getParentElement = (element, selector) => {
            if(element){
                while(element.parentElement){
                    if(element.parentElement.matches(selector)){                    
                        return element.parentElement
                    }
                    element = element.parentElement
                }

            }
        }

        const validate = (inputElement, rule, errorElement) => {
            var errorMessage
            var rules = this.selectorRules[rule.selector]            
            for (let check of rules) {
                switch(inputElement.type) {
                    case'checkbox':
                    case'radio':
                        errorMessage = check(this.formElement.querySelector(rule.selector + ':checked'))
                        break                        
                    default:
                        errorMessage = check(inputElement.value)
                }
                if(errorMessage)
                    break                
                
            }
            if (errorMessage) {
                errorElement.innerText = errorMessage
                getParentElement(inputElement, this.options.formGroupSelector).classList.add('invalid')
            }
            else {
                removeErrorMessage(errorElement, inputElement)
            }
            return !errorMessage
        }              

        if (this.formElement) {
            // submit form            
            this.formElement.onsubmit = (e) => {
                e.preventDefault()          

                var formIsValid = true
                this.options.rules.forEach(rule => {
                    var inputElement = this.formElement.querySelector(rule.selector)
                    var errorElement = getParentElement(inputElement, this.options.formGroupSelector).querySelector(this.options.errorSelector)
                    var isValid = validate(inputElement, rule, errorElement)
                    if(!isValid){
                        formIsValid = false
                    }
                })
                if(formIsValid){
                    if(typeof this.options.onSubmit === 'function'){                        
                        var enableInputs = this.formElement.querySelectorAll('[name]')                        
                        var formValues = Array.from(enableInputs).reduce((values, input) => {
                            switch(input.type){
                                case 'checkbox':
                                    if(!values[input.name]){
                                        values[input.name] = []
                                    }
                                    if(input.matches(':checked')){
                                        values[input.name].push(input.value)
                                    }
                                    break
                                case 'radio':
                                    if(input.matches(':checked'))                                        
                                        // values[input.name] = this.formElement.querySelector('input[name="'+ input.name +'"]:checked').value
                                        values[input.name] = input.value
                                    break
                                case 'file':
                                    values[input.name] = input.files
                                    break
                                default:
                                    values[input.name] = input.value
                            }
                            return values
                        }, {})
                        this.options.onSubmit(formValues)
                    }
                }
            }

            this.options.rules.forEach(rule => {
                var inputElements = this.formElement.querySelectorAll(rule.selector)

                if(Array.isArray(this.selectorRules[rule.selector])){
                    this.selectorRules[rule.selector].push(rule.test)
                }else{
                    this.selectorRules[rule.selector] = [rule.test]
                }

                Array.from(inputElements).forEach((inputElement)=> {                    
                    var errorElement = getParentElement(inputElement, this.options.formGroupSelector).querySelector(this.options.errorSelector)

                    if (inputElement) {
                        inputElement.onblur = () => {
                            validate(inputElement, rule, errorElement)
                        }
                        inputElement.oninput = () => {
                            removeErrorMessage(errorElement, inputElement)
                        }
                        inputElement.onchange = () => {
                            validate(inputElement, rule, errorElement)
                        }
                    }

                })

            });            
        }
    }
    

    static isRequire(selector, message) {
        return {
            selector,
            test: function (value) {          
                return (typeof value==='string' ? value = value.trim(): value) ? undefined : message || 'Không để trống'
            }
        }
    }
    static isEmail(selector, message) {
        return {
            selector,
            test: function (value) {
                const regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
                return regex.test(value) ? undefined : message || 'Vui lòng nhập email'
            }
        }
    }
    static isPhoneNum(selector, message) {
        return {
            selector,
            test: function (value) {
                const regex = /(84|0[3|5|7|8|9])+([0-9]{8})\b/
                return regex.test(value) ? undefined : message || 'Vui lòng nhập số điện thoại'
            }
        }
    }
    static isCharacter(selector, message) {
        return {
            selector,
            test: function (value) {
                const regex = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s\W|_]+$/
                return regex.test(value) ? undefined : message || 'Vui lòng nhập email'
            }
        }
    }
    static minLength(selector, min, message) {
        return {
            selector,
            test: function (value) {
                return value.length >= min ? undefined : message || `Vui lòng nhập tối thiểu ${min} ký tự`
            }
        }
    }
    static isConfirmed(selector, getConfirm, message) {
        return {
            selector,
            test: function (value) {
                return value === getConfirm() ? undefined : message || 'Giá trị nhập vào không khớp'
            }
        }
    }
}
export default Validator