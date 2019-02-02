import React, { Component } from 'react';

export default class BookTable extends Component {

    render() {
        return (
            <div>
                <table className="pure-table">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Price</th>
                            <th>Author</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.props.books.map(function(book) {
                                return (
                                    <tr key={book.id}>
                                        <td>{book.titulo}</td>
                                        <td>{book.preco}</td>
                                        <td>{book.autor.nome}</td>
                                    </tr>
                                );
                            })
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}