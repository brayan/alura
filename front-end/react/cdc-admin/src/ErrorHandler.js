import PubSub from 'pubsub-js';

export default class ErrorHandler {

    publishErrors(errors) {
        errors.errors.forEach(error => PubSub.publish("validation-error", error));
    }

}