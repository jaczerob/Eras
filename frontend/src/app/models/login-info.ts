export class LoginInfo {
    constructor (
        public success: string,
        public banner: string,
        
        public responseToken: string,
        
        public gameserver: string,
        public cookie: string,

        public eta: string,
        public position: string,
        public queueToken: string,
    ) {}
}