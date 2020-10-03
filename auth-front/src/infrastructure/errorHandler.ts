export default class ErrorHandler {

    public static define(callback: Function) {
        window.addEventListener("unhandledrejection", event => {
            ErrorHandler.handleError(event, callback);
        });
        window.addEventListener("error", event => {
            ErrorHandler.handleError(event, callback);
        });
    }

    private static handleError(
        event: ErrorEvent | PromiseRejectionEvent,
        callback: Function
    ) {
        console.error(event);
        if (event instanceof PromiseRejectionEvent) {
            if (event.reason.response) {
                if (
                    event.reason.response.status == 400 ||
                    event.reason.response.status == 401 ||
                    event.reason.response.status == 403
                ) {
                    callback(event.reason.response);
                    return;
                }
            }
            alert(
                `予期しないエラーが発生しました。詳細はコンソールを確認してください。${
                    event.reason
                }`
            );
        } else {
            alert(
                `予期しないエラーが発生しました。詳細はコンソールを確認してください。${
                    event.error
                }`
            );
        }
    }

}