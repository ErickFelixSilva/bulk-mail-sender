import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import EmailTemplateEditor from './components/EmailTemplateEditor';
import NonprofitList from './components/NonprofitList';
import EmailLogs from './components/EmailLogs';

function App(): JSX.Element {
  return (
    <Router>
      <div className="min-h-screen flex flex-col">
        <nav className="bg-blue-600 p-4 text-white">
          <div className="container mx-auto flex justify-between items-center">
            <Link to="/" className="text-2xl font-bold">Bulk Mail Sender</Link>
            <div className="flex space-x-4">
              <Link to="/" className="hover:underline">Home</Link>
              <Link to="/email-template" className="hover:underline">Email Template</Link>
              <Link to="/email-logs" className="hover:underline">Sent Emails</Link>
              <Link to="/nonprofits" className="hover:underline">Nonprofits</Link>
            </div>
          </div>
        </nav>
        <div className="flex-grow container mx-auto p-4">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/email-template" element={<EmailTemplateEditor />} />
            <Route path="/nonprofits" element={<NonprofitList />} />
            <Route path="/email-logs" element={<EmailLogs />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
