import React, { Component } from 'react';
import PubSub from 'pubsub-js';


export default class InputCustomizado extends Component {

    constructor() {
        super();
        this.state = { errorMessage: "" };
    }

    componentDidMount() {
        PubSub.subscribe("validation-error", function (topic, error) {
            if (error.field === this.props.name) {
                this.setState({ errorMessage: error.defaultMessage });
            }
        }.bind(this));

        PubSub.subscribe("clear-errors", function () {
            this.setState({errorMessage : ""});
        }.bind(this));
    }

    render() {
        return (
            <div className="pure-control-group">
                <label htmlFor={this.props.id}>{this.props.label}</label>
                <input {...this.props} />
                <span className="error">{this.state.errorMessage}</span>
            </div>
        );
    }

}