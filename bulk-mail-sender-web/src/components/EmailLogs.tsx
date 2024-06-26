import React, { useState, useEffect } from 'react';
import { getSentEmailLogs } from '../services/EmailService';
import axios, { CancelToken, isCancel } from 'axios';
import { handleAxiosError } from '../utils/errorHandler';

interface EmailLog {
  recipient: string;
  subject: string;
  body: string;
}

const EmailLogs: React.FC = () => {
  const [logs, setLogs] = useState<EmailLog[]>([]);

  useEffect(() => {
    const source = axios.CancelToken.source();
    const fetchLogs = async (cancelToken: CancelToken) => {
      try {
        const response = await getSentEmailLogs({ cancelToken });
        setLogs(response);
      } catch (error) {
        if (isCancel(error)) {
          console.log('Request cancelled');
        } else {
          handleAxiosError('Failed to fetch email logs', error);
        }
      }
    };

    fetchLogs(source.token);

    return () => {
      source.cancel();
    };
  }, []);

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-blue-600 text-3xl font-bold mb-4">Recently Sent Emails</h1>
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
