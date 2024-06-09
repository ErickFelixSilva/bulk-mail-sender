import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import EmailTemplateEditor from './components/EmailTemplateEditor';

function App(): JSX.Element {
  return (
    <Router>
      <div className="min-h-screen flex flex-col">
        <nav className="bg-blue-600 p-4 text-white">
          <div className="container mx-auto flex justify-between items-center">
            <Link to="/" className="text-2xl font-bold">Bulk Mail Sender</Link>
            <div>
              <Link to="/" className="mr-4 hover:underline">Home</Link>
              <Link to="/email-template" className="hover:underline">Email Template</Link>
            </div>
          </div>
        </nav>
        <div className="flex-grow container mx-auto p-4">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/email-template" element={<EmailTemplateEditor />} />
            {/* <Route path="/emails/send" element={<SendBulkEmails />} /> */}
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
