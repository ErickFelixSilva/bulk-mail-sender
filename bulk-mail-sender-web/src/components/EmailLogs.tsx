import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface EmailLog {
  recipient: string;
  subject: string;
  body: string;
}

const EmailLogs: React.FC = () => {
  const [logs, setLogs] = useState<EmailLog[]>([]);

  useEffect(() => {
    const fetchLogs = async () => {
      try {
        const response = await axios.get<EmailLog[]>('/api/emails/logs');
        setLogs(response.data);
      } catch (error) {
        console.error('Failed to fetch email logs', error);
      }
    };

    fetchLogs();
  }, []);

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-4">Sent Emails</h1>
      <table className="min-w-full bg-white">
        <thead>
          <tr>
            <th className="py-2 px-4 text-left">Recipient</th>
            <th className="py-2 px-4 text-left">Subject</th>
            <th className="py-2 px-4 text-left">Body</th>
          </tr>
        </thead>
        <tbody>
          {logs.map((log, index) => (
            <tr key={index} className="border-t">
              <td className="py-2 px-4">{log.recipient}</td>
              <td className="py-2 px-4">{log.subject}</td>
              <td className="py-2 px-4">{log.body}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmailLogs;
