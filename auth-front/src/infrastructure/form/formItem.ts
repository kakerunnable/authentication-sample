import ViewConverter from '@/infrastructure/form/converter';

export default class FormItem {
    public hasError: boolean = false;
    public type = 'text';
    public icon = '';
    public required = false;
    public pattern = '';
    public value = '';
    public placeholder = '';
    public options: FormItem[] = [];
    public converter?: ViewConverter;
    public disabled: Function = () => {
        return false;
    };
    public visible: boolean = true;

    constructor(public id: string, public name: string) {}

    get htmlId(): string {
        return 'form-' + this.id;
    }

    get convertedValue(): string {
        if (this.converter) {
            return this.converter.toView(this.value);
        }
        return this.value;
    }

    set convertedValue(value: string) {
        if (this.converter) {
            this.value = this.converter.toValue(value);
        } else {
            this.value = value;
        }
    }

    public withConverter(converter: ViewConverter): FormItem {
        this.converter = converter;
        return this;
    }

    public withType(type: string): FormItem {
        this.type = type;
        return this;
    }

    public withIcon(icon: string): FormItem {
        this.icon = icon;
        return this;
    }

    public withRequired(required: boolean): FormItem {
        this.required = required;
        return this;
    }

    public withPattern(pattern: string): FormItem {
        this.pattern = pattern;
        return this;
    }

    public withPlaceholder(placeholder: string): FormItem {
        this.placeholder = placeholder;
        return this;
    }

    public withOptions(options: FormItem[]): FormItem {
        this.options = options;
        return this;
    }

    public withValue(value: string): FormItem {
        this.value = value;
        return this;
    }

    public withDisabled(disabled: Function): FormItem {
        this.disabled = disabled;
        return this;
    }

    public withVisible(visible: boolean): FormItem {
        this.visible = visible;
        return this;
    }
}
