export class User {

    userId: string = "";
    email: string = '';

    constructor(public username: string = 'anonymouse', public roles: string[] = []){};
}