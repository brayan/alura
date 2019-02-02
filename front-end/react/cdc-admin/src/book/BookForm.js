import React, { Component } from 'react';
import $ from 'jquery';
import PubSub from 'pubsub-js';
import ErrorHandler from '../ErrorHandler';
import InputCustomizado from '../components/InputCustomizado';
import SubmitCustomizado from '../components/SubmitCustomizado';

export default class BookForm extends Component {

    constructor() {
        super();
        this.state = { title: '', price: '', authorId: '' };
        this.bindThisToMethods();
    }

    bindThisToMethods() {
        this.submitBookForm = this.submitBookForm.bind(this);
        this.onSubmitBookFormSuccess = this.onSubmitBookFormSuccess.bind(this);
        this.setTitle = this.setTitle.bind(this);
        this.setPrice = this.setPrice.bind(this);
        this.setAuthorId = this.setAuthorId.bind(this);
    }

    submitBookForm(event) {
        event.preventDefault();
        $.ajax({
            url: 'https://cdc-react.herokuapp.com/api/livros',
            contentType: 'application/json',
            dataType: 'json',
            type: 'post',
            data: JSON.stringify({ titulo: this.state.title, preco: this.state.price, autorId: this.state.authorId }),
            success: function (response) {
                this.onSubmitBookFormSuccess(response)
            }.bind(this),
            error: function (response) {
                this.onSubmitBookFormError(response);
            }.bind(this),
            beforeSend: function () {
                PubSub.publish("clear-errors", {});
            }
        });
    }

    onSubmitBookFormSuccess(response) {
        console.log("onSubmitBookFormSuccess");
        PubSub.publish("update-books", response);
        this.setState({ title: '', price: '', authorId: '' });
    }

    onSubmitBookFormError(response) {
        if (response.status === 400) {
            new ErrorHandler().publishErrors(response.responseJSON);
        }
    }

    setTitle(event) {
        this.setState({ title: event.target.value });
    }

    setPrice(event) {
        this.setState({ price: event.target.value });
    }

    setAuthorId(event) {
        this.setState({ authorId: event.target.value });
    }

    render() {
        return (
            <div className="pure-form pure-form-aligned">
                <form className="pure-form pure-form-aligned" onSubmit={this.submitBookForm} method="post">
                    <InputCustomizado id="title" type="text" name="titulo" value={this.state.title} onChange={this.setTitle} label="Title" />
                    <InputCustomizado id="price" type="text" name="preco" value={this.state.price} onChange={this.setPrice} label="Price" />
                    <div className="pure-control-group">
                        <label htmlFor="autorId">Autor</label>
                        <select id="autorId" name="autorId" value={this.state.authorId} onChange={this.setAuthorId}>
                            <option value="">Selecione um autor</option>
                            {
                                this.props.authors.map(function (author) {
                                    return <option key={author.id} value={author.id}>{author.nome}</option>;
                                })
                            }
                        </select>
                    </div>

                    <SubmitCustomizado label="Save" />
                </form>
            </div>
        );
    }

}