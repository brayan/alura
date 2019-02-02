import React, { Component } from 'react';
import BookForm from './BookForm';
import BookTable from './BookTable';
import $ from 'jquery';
import PubSub from 'pubsub-js';

export default class BookBox extends Component {

    constructor() {
        super();
        this.state = { authors: [], books: [] };
    }

    componentDidMount() {
        this.loadAuthors();
        this.loadBooks();
        this.subscribeToEvents();
    }

    loadAuthors() {
        $.ajax({
            url: 'https://cdc-react.herokuapp.com/api/autores',
            dataType: 'json',
            success: function (response) {
                this.setState({ authors: response });
            }.bind(this)
        });
    }

    loadBooks() {
        $.ajax({
            url: 'https://cdc-react.herokuapp.com/api/livros',
            dataType: 'json',
            success: function (response) {
                this.setState({ books: response });
            }.bind(this)
        });
    }

    subscribeToEvents() {
        PubSub.subscribe("atualiza-listagem-autores", function (topico, response) {
            this.setState({ authors: response });
        }.bind(this));

        PubSub.subscribe("update-books", function (topico, response) {
            this.setState({ books: response });
        }.bind(this));
    }

    render() {
        return (
            <div>
                <div className="header">
                    <h1>Books Registry</h1>
                </div>
                <div className="content" id="content">
                    <BookForm authors={this.state.authors} />
                    <BookTable books={this.state.books} />
                </div>
            </div>
        );
    }
}