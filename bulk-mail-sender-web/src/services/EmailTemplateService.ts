import axios from 'axios';

export type EmailTemplate = {
  id?: number;
  subject: string;
  body: string;
}

const API_URL = 'http://localhost:8080/api/email-template';

const getEmailTemplate = async (): Promise<EmailTemplate> => {
  const response = await axios.get<EmailTemplate>(API_URL);
  return response.data;
};

const saveEmailTemplate = async (template: EmailTemplate): Promise<EmailTemplate> => {
  const response = await axios.post<EmailTemplate>(API_URL, template);
  return response.data;
};

export { getEmailTemplate, saveEmailTemplate };
