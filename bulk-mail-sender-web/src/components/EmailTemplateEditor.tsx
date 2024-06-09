import React, { useEffect, useState } from 'react';
import { EmailTemplate, getEmailTemplate, saveEmailTemplate } from '../services/EmailTemplateService';

function EmailTemplateEditor(): JSX.Element {
  const [subject, setSubject] = useState<string>('');
  const [body, setBody] = useState<string>('');
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchTemplate = async () => {
      try {
        const template = await getEmailTemplate();
        setSubject(template.subject);
        setBody(template.body);
      } catch (error) {
        console.error('Failed to fetch email template', error);
      } finally {
        setLoading(false);
      }
    };

    fetchTemplate();
  }, []);

  const handleSaveClick = async () => {
    try {
      const template: EmailTemplate = { subject, body };
      await saveEmailTemplate(template);
      alert('Template saved successfully!');
    } catch (error) {
      console.error('Failed to save email template', error);
      alert('Failed to save template');
    }
  };

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div className="flex flex-col items-center justify-start min-h-screen bg-blue-50 m-5">
      <div className="bg-white p-10 rounded-lg shadow-md max-w-4xl w-full text-center">
        <h1 className="text-4xl font-bold text-blue-600 mb-4">Email Template Editor</h1>
        <div className="flex flex-col items-start">
          <label htmlFor="subject" className="text-lg text-gray-700 mb-2">Subject</label>
          <input
            id="subject"
            type="text"
            className="w-full p-2 border border-gray-300 rounded-lg mb-4"
            value={subject}
            onChange={(e) => setSubject(e.target.value)}
          />
        </div>
        <div className="flex flex-col items-start">
          <label htmlFor="body" className="text-lg text-gray-700 mb-2">Body</label>
          <textarea
            id="body"
            className="w-full min-h-40 p-2 border border-gray-300 rounded-lg mb-4"
            value={body}
            onChange={(e) => setBody(e.target.value)}
          />
        </div>
        <div className='flex justify-end gap-4'>
          <button className="text-blue-600 border-blue-600 border-2 px-4 py-2 rounded-lg hover:bg-blue-100">Bulk send email</button>
          <button className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700" onClick={handleSaveClick}>Save</button>
        </div>
      </div>
    </div>
  );
}

export default EmailTemplateEditor;