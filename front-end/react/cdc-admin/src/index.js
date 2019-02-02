import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import * as serviceWorker from './serviceWorker';
import createHistory from "history/createBrowserHistory";
import AutorBox from './Autor';
import Home from './Home';
import BookBox from './book/BookBox';

const history = createHistory({
    basename: "",
    forceRefresh: false
});

export { history }

ReactDOM.render(
    (<BrowserRouter history={history}>
        <App>
            <Switch>
                <Route exact path="/" component={Home} />
                <Route path="/autor" component={AutorBox} />
                <Route path="/livro" component={BookBox} />
            </Switch>
        </App>
    </BrowserRouter>),
    document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
