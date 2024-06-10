import axios, { AxiosRequestConfig } from 'axios';

export type EmailLog = {
  recipient: string;
  subject: string;
  body: string;
}

const API_URL = 'http://localhost:8080/api/emails';

const getSentEmailLogs = async (config?: AxiosRequestConfig): Promise<Array<EmailLog>> => {
  const response = await axios.get<Array<EmailLog>>(`${API_URL}/logs`, config);
  return response.data;
};

const sendEmails = async (nonprofitIds: Array<number>, config?: AxiosRequestConfig): Promise<string> => {
  const response = await axios.post<string>(API_URL, nonprofitIds, {
    ...config,
    headers: {
      'Content-Type': 'application/json',
      ...config?.headers
    }
  });
  return response.data;
};

export { getSentEmailLogs, sendEmails };
