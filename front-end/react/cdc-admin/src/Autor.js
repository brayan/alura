import React, { Component } from 'react';
import './css/pure-min.css';
import InputCustomizado from './components/InputCustomizado';
import SubmitCustomizado from './components/SubmitCustomizado';
import ErrorHandler from './ErrorHandler';

import $ from 'jquery';
import PubSub from 'pubsub-js';

class FormularioAutor extends Component {

  constructor() {
    super();
    this.state = { nome: '', email: '', senha: '' };
    this.bindThisToMethods();
  }

  bindThisToMethods() {
    this.submitForm = this.submitForm.bind(this);
  }

  submitForm(event) {
    event.preventDefault();
    $.ajax({
      url: 'https://cdc-react.herokuapp.com/api/autores',
      contentType: 'application/json',
      dataType: 'json',
      type: 'post',
      data: JSON.stringify({ nome: this.state.nome, email: this.state.email, senha: this.state.senha }),
      success: function (response) {
        PubSub.publish("atualiza-listagem-autores", response);
        this.setState({ nome: "", email: "", senha: "" });
      }.bind(this),
      error: (response) => {
        if (response.status === 400) {
          new ErrorHandler().publishErrors(response.responseJSON);
        }
      },
      beforeSend: function () {
        PubSub.publish("clear-errors", {});
      }
    });
  }

  setField(name, event) {
    this.setState({ [name]: event.target.value })
  }

  render() {
    return (
      <div className="pure-form pure-form-aligned">
        <form className="pure-form pure-form-aligned" onSubmit={this.submitForm} method="post">
          <InputCustomizado id="nome" type="text" name="nome" value={this.state.nome} onChange={this.setField.bind(this, 'nome')} label="Nome" />
          <InputCustomizado id="email" type="email" name="email" value={this.state.email} onChange={this.setField.bind(this, 'email')} label="Email" />
          <InputCustomizado id="senha" type="password" name="senha" value={this.state.senha} onChange={this.setField.bind(this, 'senha')} label="Senha" />
          <SubmitCustomizado label="Gravar" />
        </form>
      </div>
    );
  }

}

class TabelaAutores extends Component {

  render() {
    return (
      <div>
        <table className="pure-table">
          <thead>
            <tr>
              <th>Nome</th>
              <th>email</th>
            </tr>
          </thead>
          <tbody>
            {
              this.props.lista.map(function (autor) {
                return (
                  <tr key={autor.id}>
                    <td>{autor.nome}</td>
                    <td>{autor.email}</td>
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

export default class AutorBox extends Component {

  constructor() {
    super();
    this.state = { lista: [] };
  }

  componentDidMount() {
    this.loadAuthors();

    PubSub.subscribe("atualiza-listagem-autores", function (topico, response) {
      this.setState({ lista: response });
    }.bind(this));
  }

  loadAuthors() {
    $.ajax({
      url: 'https://cdc-react.herokuapp.com/api/autores',
      dataType: 'json',
      success: function (resposta) {
        this.setState({ lista: resposta });
      }.bind(this)
    });
  }

  atualizarListagem(novaLista) {
    this.setState({ lista: novaLista })
  }

  render() {
    return (
      <div>
        <div className="header">
          <h1>Cadastro de Autores</h1>
        </div>
        <div className="content" id="content">
          <FormularioAutor />
          <TabelaAutores lista={this.state.lista} />
        </div>
      </div>
    );
  }
}