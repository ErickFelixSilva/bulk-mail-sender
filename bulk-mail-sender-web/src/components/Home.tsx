import React from 'react';

function Home(): JSX.Element {
  return (
    <div className="flex flex-col items-center justify-start min-h-screen bg-blue-50 m-5">
      <div className="bg-white p-10 rounded-lg shadow-md max-w-2xl w-full text-center">
        <h1 className="text-4xl font-bold text-blue-600 mb-4">Welcome to Bulk Mail Sender</h1>
        <p className="text-gray-700 text-lg mb-4">
          This is a simple application to manage bulk emails templates. You can create and edit your email templates and send bulk emails to your recipients.
        </p>
        <p className="text-gray-700 text-lg">
          Navigate through the app using the menu to get started.
        </p>
      </div>
    </div>
  );
}

export default Home;
