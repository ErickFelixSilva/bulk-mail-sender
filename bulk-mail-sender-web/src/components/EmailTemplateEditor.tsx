import React, { useEffect, useState } from 'react';
import { EmailTemplate, getEmailTemplate, saveEmailTemplate } from '../services/EmailTemplateService';
import axios,{ CancelToken, isCancel } from 'axios';
import { useNavigate } from 'react-router-dom';

function EmailTemplateEditor(): JSX.Element {
  const [template, setTemplate] = useState<EmailTemplate>({ subject: '', body: '' });
  const [loading, setLoading] = useState<boolean>(true);
  const navigate = useNavigate();

  const fetchTemplate = async (cancelToken: CancelToken) => {
    try {
      const lastTemplate = await getEmailTemplate({ cancelToken });
      setTemplate(lastTemplate);
    } catch (error) {
      if (isCancel(error)) {
        console.log('Request cancelled');
      } else {
        console.error('Failed to fetch email template', error);
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const source = axios.CancelToken.source();

    fetchTemplate(source.token);

    return () => {
      source.cancel();
    };
  }, []);

  const handleSaveClick = async () => {
    try {
      const savedTemplate = await saveEmailTemplate(template);
      setTemplate(savedTemplate);
      alert('Template saved successfully!');
    } catch (error) {
      console.error('Failed to save email template', error);
      alert('Failed to save template');
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setTemplate((prevTemplate) => ({
      ...prevTemplate,
      [name]: value,
    }));
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
            name="subject"
            className="w-full p-2 border border-gray-300 rounded-lg mb-4"
            value={template.subject}
            onChange={handleChange}
          />
        </div>
        <div className="flex flex-col items-start">
          <label htmlFor="body" className="text-lg text-gray-700 mb-2">Body</label>
          <textarea
            id="body"
            name="body"
            className="w-full min-h-40 p-2 border border-gray-300 rounded-lg mb-4"
            value={template.body}
            onChange={handleChange}
          />
        </div>
        <div className='flex justify-end gap-4'>
          <button className="text-blue-600 border-blue-600 border-2 px-4 py-2 rounded-lg hover:bg-blue-100" onClick={() => navigate('/nonprofits')}>Bulk send email</button>
          <button className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700" onClick={handleSaveClick}>Save</button>
        </div>
      </div>
    </div>
  );
}

export default EmailTemplateEditor;