export default interface Converter {
    toView(value:string):string;
    toValue(view:string): string;
}